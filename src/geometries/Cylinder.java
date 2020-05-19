/**
 * 
 */
package geometries;

import java.util.List;
import primitives.*;

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
		Vector _direction=super.get_axisRay().get_direction().normalize();
		Point3D _center=super.get_axisRay().get_p();
		Plane _basic1=new Plane(_center,_direction);
		Plane _basic2=new Plane(_center.add(_direction.scale(_height)),_direction);
		//check if the point exist in plane basic1:
		Vector _v=_point.subtract(_basic1._p);
		if(Util.isZero(_v.dotProduct(_basic1._normal)))
			//if the point exist on the basic1 plane- returns the normal to the plane
			return _basic1._normal;
		//check if the point exist in plane basic2:
		_v=_point.subtract(_basic2._p);
		if(Util.isZero(_v.dotProduct(_basic2._normal)))
			//if the point exist on the basic2 plane- returns the normal to the plane
			return _basic2._normal;
		//if not exist in both basics- it's a sign that the point exist on the side of the cylinder
		return super.getNormal(_point);
	}
	
	@Override
	public String toString() {
		return "_height=" + _height+" "+super.toString();
	}
	
	 @Override
	    public List<GeoPoint> findIntersections(Ray ray) {
	        return super.findIntersections(ray);
	    }
}
