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
public class Cylinder extends Tube
{
	double _height;
 
	/**
	 * @param _radius
	 * @param _axisRay
	 * @param _height
	 */
	public Cylinder(double _radius, Ray _axisRay, double _height) {
		super(_radius, _axisRay);
		this._height = _height;
	}
	
	/**
	 * @return the _height
	 */
	public double get_height() {
		return _height;
	}

	public Vector getNormal(Point3D _point)
	{ 
		return super.getNormal(_point);
	}
	
	@Override
	public String toString() {
		return "_height=" + _height+" "+super.toString();
	}
}
