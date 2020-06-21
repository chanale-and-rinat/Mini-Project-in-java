/**
 * 
 */
package renderer;
import java.util.LinkedList;
import static primitives.Util.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Color;
import static primitives.Util.alignZero;

import java.util.ArrayList;
import java.util.List;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import elements.*;
import primitives.*;
import scene.*;

/**
 * @author rinat
 *
 */
public class render {
	ImageWriter _image_writer;
	Scene _scene;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	Random rand = new Random();

	/**
	 * @param _image_writer
	 * @param _scene
	 */
	public render(ImageWriter _image_writer, Scene _scene) {
		super();
		this._image_writer = _image_writer;
		this._scene = _scene;
	}
	public void renderImage() {
		renderImage(1);
	}
	public void renderImage(int numberOfRays) {
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		java.awt.Color background = _scene.get_background().getColor();
		int nX = _image_writer.getNx();
		int nY=_image_writer.getNy();
		
		// i is pixel row number and j is pixel in the row number
		for(int i=0;i<nX;i++)
		{
			for(int j=0;j<nY;j++)
			{
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, _scene.get_distance(),_image_writer.getWidth(), _image_writer.getHeight());
				List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
				if( intersectionPoints==null)
				{
					_image_writer.writePixel(j, i, background);
				}
				else
				{
					GeoPoint closestPoint = getClosestPoint(intersectionPoints);
					_image_writer.writePixel(j, i, calcColor(closestPoint,ray,numberOfRays));
				}
			}
		}
		
		
	}

	
	private Color calcColor(GeoPoint geoPoint, Ray inRay,int numberOfRays) {
        primitives.Color color =new  primitives.Color( calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0,numberOfRays));
        color = color.add(_scene.get_ambientLight().getIntensity());
        return color.getColor();
    }

    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k,int numberOfRays) {
        if (level == 1 || k < MIN_CALC_COLOR_K) {
            return Color.BLACK;
        }

        primitives.Color result = geoPoint.getGeometry().getEmissionLight();
        Point3D pointGeo = geoPoint.getPoint();

        Vector v = pointGeo.subtract(_scene.get_camera().getLocation()).normalize();
        Vector n = geoPoint.getGeometry().getNormal(pointGeo);

        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            //ray parallel to geometry surface ??
            //and orthogonal to normal
            return result.getColor();
        }

        Material material = geoPoint.getGeometry().getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        double kr = geoPoint.getGeometry().getMaterial().get_kR();
        double kt = geoPoint.getGeometry().getMaterial().get_kT();
        double kkr = k * kr;
        double kkt = k * kt;
    result = result.add(getLightSourcesColors(geoPoint, k, result, v, n, nv, nShininess, kd, ks,numberOfRays));


        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(pointGeo, inRay, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                result = result.add(new primitives.Color(calcColor(reflectedPoint, reflectedRay, level - 1, kkr,numberOfRays)).scale(kr));
            }
        }
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(pointGeo, inRay, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                result = result.add(new primitives.Color( calcColor(refractedPoint, refractedRay, level - 1, kkt,numberOfRays)).scale(kt));
            }
        }
        return result.getColor();
    }
    
    private primitives.Color getLightSourcesColors(GeoPoint geoPoint, double k, primitives.Color result, Vector v, Vector n, double nv, int nShininess, double kd, double ks,int numberOfRays) {
        Point3D pointGeo = geoPoint.getPoint();
        List<LightSource> lightSources = _scene.get_lights();
        if (lightSources != null) {
            for (LightSource lightSource : lightSources) {
                Vector l = lightSource.getL(pointGeo);
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) {
//                if (sign(nl) == sign(nv) && nl != 0 && nv != 0) {
//                    if (unshaded(lightSource, l, n, geoPoint)) {
                	double ktr;
                    	ktr = transparency(lightSource, l, n, geoPoint,numberOfRays);
                	
                    if (ktr * k > MIN_CALC_COLOR_K) {
                        primitives.Color ip =(new primitives.Color( lightSource.getIntensity(pointGeo))).scale(ktr);
                        result = result.add(                        	
                                calcDiffusive(kd, nl, ip),
                                calcSpecular(ks, l, n, nl, v, nShininess, ip));
                    }
                }
            }
        }
        return result;
    }
    
    /**
     * Calculate refracted ray
     *
     * @param point, ray,vector
     * @return the ray
     */
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.get_direction(), n);
    }
    /**
     * Calculate Reflected ray
     *
     * @param point, ray,vector
     * @return the ray
     */
    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {
        //𝒓=𝒗 −𝟐∙(𝒗∙𝒏)∙𝒏
        Vector v = inRay.get_direction();
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }

	
	/**
     * Calculate the color intensity in a point
     *
     * @param gp intersection the point for which the color is required
     * @return the color intensity
     */
	/*
    private Color calcColor(GeoPoint gp) {
        primitives.Color result = _scene.get_ambientLight().getIntensity();
        result = result.add(gp.getGeometry().getEmissionLight());
        List<LightSource> lights = _scene.get_lights();

        Vector v = gp.getPoint().subtract(_scene.get_camera().getLocation()).normalize();
        Vector n = gp.getGeometry().getNormal(gp.getPoint());

        Material material = gp.getGeometry().getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        if (_scene.get_lights()!= null) {
            for (LightSource lightSource : lights) {

                Vector l = lightSource.getL(gp.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));

                if (sign(nl) == sign(nv)) {
                    primitives.Color ip = lightSource.getIntensity(gp.getPoint());
                    result = result.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n, nl, v, nShininess, ip)
                    );
                }
            }
        }

        return result.getColor();
    }
    */
    /**
     * Find intersections of a ray with the scene geometries and get the
     * intersection point that is closest to the ray head. If there are no
     * intersections, null will be returned.
     *
     * @param ray intersecting the scene
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {

        if (ray == null) {
            return null;
        }

        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.get_p();

        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(ray);
        if (intersections == null)
            return null;

        for (GeoPoint geoPoint : intersections) {
            double distance = ray_p0.distance(geoPoint.getPoint());
            if (distance < closestDistance) {
                closestPoint = geoPoint;
                closestDistance = distance;
            }
        }
        return closestPoint;
    }
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
		GeoPoint result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.get_camera().getLocation();

        for (GeoPoint geo : intersectionPoints) {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist) {
                mindist = distance;
                result = geo;
            }
        }
        return result;
	}
	public void printGrid(int k, java.awt.Color color) {
		for(int i=0;i<_image_writer.getNx();i++)
		{
			for(int j=0;j<_image_writer.getNy();j++)
			{
				if(i%k==0 || j%k==0)
				{
					_image_writer.writePixel(i, j, color);
				}
			}
		}
		_image_writer.writeToImage();
		
	}
	public void writeToImage() {
		_image_writer.writeToImage();		
	} 
	private boolean sign(double val) {
        return (val > 0d);
    }
	/**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component coef
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param v          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     * @author Dan Zilberstein
     * <p>
     * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
     * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
     * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
     * the surface.
     */
    private primitives.Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, primitives.Color ip) {
        double p = nShininess;

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(R.dotProduct(v));
        if (minusVR <= 0) {
            return new primitives.Color(Color.BLACK); // view from direction opposite to r vector
        }
        return ip.scale(ks * Math.pow(minusVR, p));
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     * @author Dan Zilberstein
     * <p>
     * The diffuse component is that dot product n•L that we discussed in class. It approximates light, originally
     * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
     * surface is paper. In general, you'll also want this to have a non-gray color value, so this term would in general
     * be a color defined as: [rd,gd,bd](n•L)
     */
    private primitives.Color calcDiffusive(double kd, double nl, primitives.Color ip) {
        if (nl < 0) nl = -nl;
        return ip.scale(nl * kd);
    }
    private double unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint,double radiusOfLight,int numberOfRay) {
    	int c=0;
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D pointGeo = geopoint.getPoint();
        
        Vector vx=new Vector(1,0,0);
        Vector vy=new Vector(0,1,0);
        // pC - center of the circle
        // p0 - start of central ray, v - its direction, distance - from p0 to pC
        Point3D p0=geopoint.getPoint();
        Point3D pC = p0.add(lightDirection.scale(light.getDistance(p0)));
    
        for(int i=0;i<numberOfRay;i++)
        {
     // create random polar system coordinates of a point in circle of radius r
     double cosTeta = Math.random()*((1-(-1))+1)+(-1);
     double sinTeta = Math.sqrt(1 - cosTeta*cosTeta); // זהות בסיסית בטריגו
     double d = Math.random()*((radiusOfLight-(-radiusOfLight))+radiusOfLight)+(-radiusOfLight);
     // Convert polar coordinates to Cartesian ones
     double x = d*cosTeta;
     double y = d*sinTeta;
    
     Point3D point = pC;
     if ((x)!=0) point = point.add(vx.scale(x));
     if ((y)!=0) point = point.add(vy.scale(y));
     Ray currentRay=new Ray(p0, point.subtract(p0)); // normalized inside Ray ctor
        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(currentRay);
        
        if (intersections != null) {
           
        double lightDistance = light.getDistance(pointGeo);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0
                    && gp.getGeometry().getMaterial().get_kT() == 0) {
                c++;
            }
        }
        }
        }
        return (double)c/numberOfRay;
    }
  private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D pointGeo = geopoint.getPoint();

        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        double lightDistance = light.getDistance(pointGeo);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0
                    && gp.getGeometry().getMaterial().get_kT() == 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * 
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return number of transparency
     */
 /*
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint,int numberOfRays) {
    	
    	
    	
    	
    	double c=0;
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
        Point3D pointGeo = geopoint.getPoint();
        //Vector v = lightRay.get_direction();
        //Vector vx = (new Vector(-v.get_head().get_y().get(), v.get_head().get_x().get(),0)).normalized();
        //Vector vy = (v.crossProduct(vx)).normalized();
        Vector vx=new Vector(1,0,0);
        Vector vy=new Vector(0,1,0);
        // pC - center of the circle
        // p0 - start of central ray, v - its direction, distance - from p0 to pC
        Point3D p0=geopoint.getPoint();
        Point3D pC = p0.add(lightDirection.scale(light.getDistance(p0)));
        
        //if numberOfRays is 1 we will build the ray to be the direction to the center of the light
        if(numberOfRays==1) {
        	List<GeoPoint> intersections = _scene.get_geometries().findIntersections(lightRay);
            if (intersections == null) {
                return 1d;
            }
            double lightDistance = light.getDistance(pointGeo);
            double ktr = 1d;
            for (GeoPoint gp : intersections) {
                if (alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0) {
                    ktr *= gp.getGeometry().getMaterial().get_kT();
                    if (ktr < MIN_CALC_COLOR_K) {
                        return 0.0;
                    }
                }
            }
            return ktr;
        }
        else {
        double radiusOfLight=light.getRadius();
        	
        double ktr=1d;
        for(int i=0;i<numberOfRays;i++)
        {
        	
		     // create random polar system coordinates of a point in circle of radius r
		     //double cosTeta = Math.random()*((1-(-1))+1)+(-1);
        	double cosTeta=ThreadLocalRandom.current().nextDouble(-1, 1);
		     double sinTeta = Math.sqrt(1 - cosTeta*cosTeta); // זהות בסיסית בטריגו
		     //double d = Math.random()*((radiusOfLight-(-radiusOfLight))+radiusOfLight)+(-radiusOfLight);
		     double d =ThreadLocalRandom.current().nextDouble(0, radiusOfLight);
		     // Convert polar coordinates to Cartesian ones
		     double x = d*cosTeta;
		     double y = d*sinTeta;
		     
		    
		     Point3D point = pC;
		     if ((x)!=0) point = point.add(vx.scale(x));
		     if ((y)!=0) point = point.add(vy.scale(y));
		     Ray currentRay=new Ray(p0, (point.subtract(p0)).normalize()); // normalized inside Ray ctor
		        List<GeoPoint> intersections = _scene.get_geometries().findIntersections(currentRay);
  	
		        ktr = 1d;
		        if (intersections == null) {
		             ktr=1d;
		        }
		        else
		        {
			        double lightDistance = light.getDistance(pointGeo);
			       
			        for (GeoPoint gp : intersections) {
			            if ( alignZero(gp.getPoint().distance(pointGeo) - lightDistance) <= 0) {
			                ktr *= gp.getGeometry().getMaterial().get_kT();
			                if (ktr < MIN_CALC_COLOR_K) {
			                    ktr=0.0;
			                    break;
			                }
			            }
			        }
			        
			     }//end else
		         
		         c+=ktr;
        }//end for
        return c/numberOfRays;
        }
    }
	*/
  
  private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint,int numberOfRays) {
	  double sum_ktr = 0;
      List<Ray> rays = constructRaysToLight(light, l, n, geopoint,numberOfRays);
      for (Ray ray : rays) {
          List<GeoPoint> intersections = _scene.get_geometries().findIntersections(ray);
          if (intersections != null) {
              double lightDistance = light.getDistance(geopoint.getPoint());
              double ktr = 1;
              for (GeoPoint geo : intersections) {
                  if (alignZero(geo.getPoint().distance(geopoint.getPoint()) - lightDistance) <= 0) {
                      ktr *= geo.getGeometry().getMaterial().get_kT();
                      if (ktr < MIN_CALC_COLOR_K) {
                          ktr = 0;
                          break;
                      }
                  }
              }
              sum_ktr += ktr;
          } else
              sum_ktr += 1;
      }
      return sum_ktr/rays.size();
  }

  private List<Ray> constructRaysToLight(LightSource light, Vector l, Vector n, GeoPoint geopoint,int numberOfRays){
      Vector lightDirection = l.scale(-1); // from point to light source
      Ray lightRay = new Ray(geopoint.getPoint(), lightDirection, n);
      List<Ray> beam = new ArrayList<>();
      beam.add(lightRay);
        if (light.getRadius() == 0) return beam;
      Point3D p0 = lightRay.get_p();
      Vector v = lightRay.get_direction();
      Vector vx = (new Vector(-v.get_head().get_y().get(), v.get_head().get_x().get(),0)).normalized();
      Vector vy = (v.crossProduct(vx)).normalized();
      double r = light.getRadius();
      Point3D pC = lightRay.getTargetPoint(light.getDistance(p0));
      for (int i=0; i<numberOfRays-1; i++){
          // create random polar system coordinates of a point in circle of radius r
          double cosTeta = ThreadLocalRandom.current().nextDouble(-1, 1);
          double sinTeta = Math.sqrt(1 - cosTeta*cosTeta);
          double d = ThreadLocalRandom.current().nextDouble(0, r);
          // Convert polar coordinates to Cartesian ones
          double x = d*cosTeta;
          double y = d*sinTeta;
          // pC - center of the circle
          // p0 - start of central ray, v - its direction, distance - from p0 to pC
          Point3D point = pC;
          if (!isZero(x)) point = point.add(vx.scale(x));
          if (!isZero(y)) point = point.add(vy.scale(y));
          beam.add(new Ray(p0, point.subtract(p0))); // normalized inside Ray ctor
      }
      return beam;
  }
  
}
