/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import primitives.Util;
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
}
