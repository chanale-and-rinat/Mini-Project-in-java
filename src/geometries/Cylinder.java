/**
 * 
 */
package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author ψημι
 *
 */
public class Cylinder extends RadialGeometry
{
	@Override
	public String toString() {
		return "_height=" + _height;
	}

	/**
	 * @return the _height
	 */
	public double get_height() {
		return _height;
	}

	/**
	 * @param _radius
	 * @param _height
	 */
	public Cylinder(double _radius, double _height) {
		super(_radius);
		this._height = _height;
	}

	double _height;
	
	public Vector getNormal(Point3D _point)
	{
		return null;
	}

}
