/**
 * 
 */
package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
/**
 * @author ψημι
 *
 */
public class Tube extends RadialGeometry {
	
	Ray _axisRay;

	/**
	 * @param _radius
	 * @param _axisRay
	 */
	public Tube(double _radius, Ray _axisRay) {
		super(_radius);
		this._axisRay = _axisRay;
	}

	/**
	 * @return the _axisRay
	 */
	public Ray get_axisRay() {
		return _axisRay;
	}
	@Override
	public Vector getNormal(Point3D _point)
	{
		Point3D _o=_axisRay.get_p();
		Vector _v=_axisRay.get_direction();
		//projection of P-O on the ray:
		double _t=_point.subtract(_o).dotProduct(_v);
		if(!Util.isZero(_t))//if it's close to 0, we'll get ZERO vector exception
			_o=_o.add(_v.scale(_t));
		return _point.subtract(_o).normalize();
	}
	
	@Override
	public String toString() {
		return "axisRay=" + _axisRay;
	}
	
	 /** Function for finding intersections points with an infinite
	  * tube.
	  * @param ray The ray that we check if it intersects the tube.
	  * @return A list of intersection points, if any.
	  */
	  @Override
	 public List<Point3D> findIntersections(Ray ray) {
		  return null;
	  }
		  /*List<Point3D> toReturn = new List<Point3D>();
	   
	   Point3D P = ray.get_p();
	   
	   Vector V = ray.get_direction(),
	        Va =_axisRay.get_direction(),
	        DeltaP = new Vector(P.subtract(_axisRay.get_p())),
	        temp_for_use1, temp_for_use2;
	   
	   double V_dot_Va = V.dotProduct(Va),
	        DeltaP_dot_Va = DeltaP.dotProduct(Va);
	   
	   temp_for_use1 = V.subtract(Va.scale(V_dot_Va));
	   temp_for_use2 = DeltaP.subtract(Va.scale(DeltaP_dot_Va));
	   
	   double A = temp_for_use1.dotProduct(temp_for_use1);
	   double B = 2*V.subtract(Va.scale(V_dot_Va)).dotProduct(DeltaP.subtract(Va.scale(DeltaP_dot_Va)));
	   double C = temp_for_use2.dotProduct(temp_for_use2) - _radius * _radius;
	   double desc = calcs.subtract(B*B, 4*A*C);
	   
	   if (desc < 0) {//No solution
	     return toReturn;
	   }
	   
	   double t1 = (-B+Math.sqrt(desc))/(2*A),
	        t2 = (-B-Math.sqrt(desc))/(2*A);
	   
	   if (desc == 0) {//One solution
	     if (-B/(2*A) < 0)
	       return toReturn;
	     toReturn.add(new Vector(P.add(V.scale(-B/(2*A)).getHead())).getHead());
	     return toReturn;
	   }
	   else if (t1 < 0 && t2 < 0){
	     return toReturn;
	   }
	   else if (t1 < 0 && t2 > 0) {
	     toReturn.add(new Vector(P.add(V.scale(t2).getHead())).getHead());
	     return toReturn;
	   }
	   else if (t1 > 0 && t2 < 0) {
	     toReturn.add(new Vector(P.add(V.scale(t1).getHead())).getHead());
	     return toReturn;
	   }
	   else {
	     toReturn.add(new Vector(P.add(V.scale(t1).getHead())).getHead());
	     toReturn.add(new Vector(P.add(V.scale(t2).getHead())).getHead());
	     return toReturn;
	   }
	 }ώ
*/
}
