/**
 * 
 */
package geometries;

import primitives.*;
import java.util.List;

import geometries.Intersectable.Box;
/**
 * @author ψημι
 *
 */
public interface Intersectable {
	/**
     * @param ray ray pointing toward a Gepmtry
     * @return List<GeoPoint> return values
     */
    List<GeoPoint> findIntersections(Ray ray);
    boolean IsIntersectionBox(Ray ray);
    Box get_box() ;
    /**
     * GeoPoint is just a tuple holding
     * references to a specific point ain a specific geometry
     */
    public static class GeoPoint {

        protected final Geometry _geometry;
        protected final Point3D point;

        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            point = pt;
        }

        public Point3D getPoint() {
            return point;
        }

        public Geometry getGeometry() {
            return _geometry;
        }
    }
    public static class Box {


		/**
		 * @return the x0
		 */
		public double getX0() {
			return x0;
		}

		/**
		 * @return the x1
		 */
		public double getX1() {
			return x1;
		}

		/**
		 * @return the y0
		 */
		public double getY0() {
			return y0;
		}

		/**
		 * @return the y1
		 */
		public double getY1() {
			return y1;
		}

		/**
		 * @return the z0
		 */
		public double getZ0() {
			return z0;
		}

		/**
		 * @return the z1
		 */
		public double getZ1() {
			return z1;
		}

		protected final double x0;
        protected final double x1;
        
        protected final double y0;
        protected final double y1;
        
        protected final double z0;
        protected final double z1;
		/**
		 * @param x0
		 * @param x1
		 * @param y0
		 * @param y1
		 * @param z0
		 * @param z1
		 */
		public Box(double x0, double x1, double y0, double y1, double z0, double z1) {
			super();
			this.x0 = x0;
			this.x1 = x1;
			this.y0 = y0;
			this.y1 = y1;
			this.z0 = z0;
			this.z1 = z1;
		}
		
        public boolean IntersectionBox(Ray r)
        {
        	double tmin = (x0 - r.get_p().get_x().get()) / r.get_direction().get_head().get_x().get(); 
        	double tmax = (x1 - r.get_p().get_x().get()) / r.get_direction().get_head().get_x().get(); 
    	 
    	    if (tmin > tmax) {
    	    	double temp=tmin;
    			tmin=tmax;
    			tmax=temp;
    	    }
    	    
    	    double tymin = (y0 - r.get_p().get_y().get()) / r.get_direction().get_head().get_y().get(); 
        	double tymax = (y1 - r.get_p().get_y().get()) / r.get_direction().get_head().get_y().get(); 
    	 
    	    if (tymin > tymax) {
    	    	double temp=tymin;
    			tymin=tymax;
    			tymax=temp;
    	    }
    	    
    	
    	 
    	    if ((tmin > tymax) || (tymin > tmax)) 
    	        return false; 
    	 
    	    if (tymin > tmin) 
    	        tmin = tymin; 
    	 
    	    if (tymax < tmax) 
    	        tmax = tymax; 
    	 
    	    double tzmin = (z0 - r.get_p().get_z().get()) / r.get_direction().get_head().get_z().get(); 
        	double tzmax = (z1 - r.get_p().get_z().get()) / r.get_direction().get_head().get_z().get(); 
    	 
    	    if (tzmin > tzmax) {
    	    	double temp=tzmin;
    			tzmin=tzmax;
    			tzmax=temp;
    	    }
    	 
    	    if ((tmin > tzmax) || (tzmin > tmax)) 
    	        return false; 
    	 
    	    if (tzmin > tmin) 
    	        tmin = tzmin; 
    	 
    	    if (tzmax < tmax) 
    	        tmax = tzmax; 
    	 
    	    return true;     	           
        }

        
    }

}
