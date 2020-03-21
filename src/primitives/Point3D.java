package primitives;

/**
 * @author ψημι
 *
 */
public class Point3D {

public final static Point3D ZERO=new Point3D(0.0,0.0,0.0);
Coordinate _x;
Coordinate _y;
Coordinate _z;
/**
 * @param _x
 * @param _y
 * @param _z
 */
public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
	super();
	this._x = _x;
	this._y = _y;
	this._z = _z;
}
/**
 * @param point
 */
public Point3D(Point3D p) {
	super();
	this._x = p.get_x();
	this._y = p.get_y();
	this._z = p.get_z();
}
/**
 * @param _x
 * @param _y
 * @param _z
 */
public Point3D(double _x, double _y, double _z) {
	this(new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
}
/**
 * @return the _x
 */
public Coordinate get_x() {
	return new Coordinate(_x);
}
/**
 * @return the _y
 */
public Coordinate get_y() {
	return new Coordinate(_y);
}
/**
 * @return the _z
 */
public Coordinate get_z() {
	return new Coordinate(_z);
}
/**
 * @return the vector between this point to another
 */
public Vector subtract(Point3D _p)
{
	return new Vector(this.get_x().get()-_p.get_x().get(),this.get_y().get()-_p.get_y().get(),this.get_z().get()-_p.get_z().get());
}
/**
 * @return add vector to this point
 */
public Point3D add(Vector _v)
{
	return new Point3D(this.get_x().get()+_v.get_head().get_x().get(),this.get_y().get()+_v.get_head().get_y().get(),this.get_z().get()+_v.get_head().get_z().get());
}
public double	distanceSquare(Point3D _p) 
{
	return ((this.get_x().get()-_p.get_x().get())*(this.get_x().get()-_p.get_x().get()))+((this.get_y().get()-_p.get_y().get())*(this.get_y().get()-_p.get_y().get()))+((this.get_z().get()-_p.get_z().get())*(this.get_z().get()-_p.get_z().get()));
}
public double	distance(Point3D _p) 
{
	return Math.sqrt(this.distanceSquare(_p));
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Point3D other = (Point3D) obj;
	if (_x == null) {
		if (other._x != null)
			return false;
	} else if (!_x.equals(other._x))
		return false;
	if (_y == null) {
		if (other._y != null)
			return false;
	} else if (!_y.equals(other._y))
		return false;
	if (_z == null) {
		if (other._z != null)
			return false;
	} else if (!_z.equals(other._z))
		return false;
	return true;
}
@Override
public String toString() {
	return "x=" + _x + ", y=" + _y + ", z=" + _z;
}
}
