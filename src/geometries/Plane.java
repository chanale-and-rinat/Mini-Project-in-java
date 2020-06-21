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
public class Plane extends Geometry{
	
Point3D _p;
Vector _normal;

/**
 * @param _p
 * @param _normal
 */
public Plane(Point3D _p, Vector _normal) {
	super(new Box(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,
			Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY));
	this._p = _p;
	this._normal = _normal;
}
/**
 * @param color
 * @param material
 * @param 3 point3D
 */
public Plane(Color emissionLight, Material material, Point3D _p1, Point3D _p2, Point3D _p3) {
    super(emissionLight, material,
    		new Box(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,
    				Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY));

    _p = new Point3D(_p1);
	Vector _v1=_p2.subtract(_p1);
	Vector _v2=_p3.subtract(_p2);
	this._normal = new Vector(_v1.crossProduct(_v2).normalize());

}
public Plane(Color emissionLight, Material material, Point3D _p, Vector _normal) {
    super(emissionLight, material,
    		new Box(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,
    				Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY));
    this._p = _p;
	this._normal = _normal;

}
/**
 * @param color
 * @param 3 point3D
 */
public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
    this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
}

/**
 * @param _p1
 * @param _p2
 * @param _p3
 */
public Plane(Point3D _p1, Point3D _p2,Point3D _p3) {
	
	super(new Box(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY));
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
public List<GeoPoint> findIntersections(Ray ray) {
	if(!IsIntersectionBox(ray))
	{
		return null;
	}
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

    if (t <= 0) {
        return null;
    }

    GeoPoint geo = new GeoPoint(this, ray.getTargetPoint(t));
    return List.of(geo);
}
@Override
public boolean IsIntersectionBox(Ray ray) {
	return this._box.IntersectionBox(ray);
}
}
