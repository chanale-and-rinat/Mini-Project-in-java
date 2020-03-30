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
	this._p = _p1;
	Vector _v1=_p2.subtract(_p1);
	Vector _v2=_p3.subtract(_p2);
	this._normal = new Vector(_v1.crossProduct(_v2).normalize());
}

@Override
public Vector getNormal(Point3D _p) {
	return _normal;
}
}
