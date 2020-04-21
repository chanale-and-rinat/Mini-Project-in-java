/**
 * 
 */
package geometries;
import java.util.List;
import static primitives.Util.*;
import primitives.*;

/**
 * @author ψημι
 *
 */
public class Plane implements Geometry{
	
Point3D _p;
Vector _normal;

/**
 * @param _p
 * @param _normal
 */
public Plane(Point3D _p, Vector _normal) {
	super();
	this._p = _p;
	this._normal = _normal;
}

/**
 * @param _p1
 * @param _p2
 * @param _p3
 */
public Plane(Point3D _p1, Point3D _p2,Point3D _p3) {
	
	super();
	try {
	this._p = _p1;
	Vector _v1=_p2.subtract(_p1);
	Vector _v2=_p3.subtract(_p2);
	this._normal = new Vector(_v1.crossProduct(_v2).normalize());
	}
	catch(IllegalArgumentException e)
	{
		throw new IllegalArgumentException("your points are on the same ray");
	}
}

@Override
public Vector getNormal(Point3D _p) {
	return _normal;
}

@Override
public List<Point3D> findIntersections(Ray ray) {
    Vector p0Q;
    try {
        p0Q = _p.subtract(ray.get_p());
    } catch (IllegalArgumentException e) {
        return null; // ray starts from point Q - no intersections
    }

    double nv = _normal.dotProduct(ray.get_direction());
    if (isZero(nv)) // ray is parallel to the plane - no intersections
        return null;

    double t = alignZero(_normal.dotProduct(p0Q) / nv);

    return t <= 0 ? null : List.of(ray.getTargetPoint(t));
}
}
