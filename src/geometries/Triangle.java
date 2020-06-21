/**
 * 
 */
package geometries;
import static primitives.Util.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ψημι
 *
 */
public class Triangle extends Polygon {
	/**
	 * @param color
	 * * @param material
	 * * @param color3 point3D
	 */
	public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,material,p1,p2,p3);
     }
	/**
	 * @param color
	 * * @param color3 point3D
	 */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,p1, p2, p3);
      }
	/**
	 * @param vertices
	 */
	public Triangle(Point3D _p1,Point3D _p2,Point3D _p3) {
		super(_p1,_p2,_p3);
	}
	
	
	@Override
    public List<GeoPoint> findIntersections(Ray ray) {
		if(!IsIntersectionBox(ray))
    	{
    		return null;
    	}
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray);
        if (planeIntersections == null) return null;

        Point3D p0 = ray.get_p();
        Vector v = ray.get_direction();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            //for GeoPoint
            List<GeoPoint> result = new LinkedList<>();
            for (GeoPoint geo : planeIntersections) {
                result.add(new GeoPoint(this, geo.getPoint()));
            }
            return result;
        }

        return null;

    }
	
 
}
