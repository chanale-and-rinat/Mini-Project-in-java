/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
	public String toString() {
		return "axisRay=" + _axisRay;
	}
	public Vector getNormal(Point3D _point)
	{
		return null;
	}
	

}
