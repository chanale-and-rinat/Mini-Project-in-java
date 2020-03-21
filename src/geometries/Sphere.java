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
public class Sphere extends RadialGeometry
{
	Point3D _center;
	
	public Vector getNormal(Point3D _point)
	{
		return null;
	}

	/**
	 * @param _radius
	 * @param _center
	 */
	public Sphere(double _radius, Point3D _center) {
		super(_radius);
		this._center = _center;
	}

	@Override
	public String toString() {
		return "center=" + _center;
	}

	/**
	 * @return the _center
	 */
	public Point3D get_center() {
		return _center;
	}

	
}
