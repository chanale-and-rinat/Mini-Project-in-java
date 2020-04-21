/**
 * 
 */
package primitives;
import static primitives.Util.*;

/**
 * @author ψημι
 *
 */
public class Ray {
	
Point3D _p;
Vector _direction;

/**
 * @param _p
 * @param _direction
 */
public Ray(Point3D _p, Vector _direction) {
	super();
	this._p = _p;
	this._direction = _direction.normalize();
}

/**
 * @author  c&r
 * @param length
 * @return new Point3D
 */
public Point3D getTargetPoint(double length) {
       return isZero(length ) ? _p : _p.add(_direction.scale(length));
}

/**
 * @return the _p
 */
public Point3D get_p() {
	return _p;
}

/**
 * @return the _direction
 */
public Vector get_direction() {
	return _direction;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Ray other = (Ray) obj;
	if (_direction == null) {
		if (other._direction != null)
			return false;
	} else if (!_direction.equals(other._direction))
		return false;
	if (_p == null) {
		if (other._p != null)
			return false;
	} else if (!_p.equals(other._p))
		return false;
	return true;
}

@Override
public String toString() {
	return "p=" + _p + ", direction=" + _direction;
}

}
