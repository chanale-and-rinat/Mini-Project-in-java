/**
 * 
 */
package primitives;

/**
 * @author ψημι
 *
 */
public class Vector {
	Point3D _head;

	/**
	 * @param point _head
	 */
	public Vector(Point3D _headOther) {
		super();
		if(_headOther.equals(Point3D.ZERO))
			throw new IllegalArgumentException("the vector can't be the zero vector");
		this._head = _headOther;
	}
	
	/**
	 * @param vector _other
	 */
	public Vector(Vector _other) {
		super();
		this._head = _other.get_head();
	}
	
	/**
	 * @param coordinate _x
	 * @param coordinate _y
	 * @param coordinate _z
	 */
	public Vector(Coordinate _x,Coordinate _y,Coordinate _z) 
	{
		super();
		if(Point3D.ZERO.equals(new Point3D(_x,_y,_z)) )
			throw new IllegalArgumentException("the vector can't be the zero vector");
		this._head=new Point3D(_x,_y,_z);
	}
	
	/**
	 * @param double _x
	 * * @param double _y
	 * * @param double _z
	 */
	public Vector(double _x,double _y, double _z) {
		super();
		if(Point3D.ZERO.equals(new Point3D(_x,_y,_z) ))
			throw new IllegalArgumentException("the vector can't be the zero vector");
		this._head = new Point3D(_x,_y,_z);
	}
	
	/**
	 * @return the _head
	 */
	public Point3D get_head() {
		return _head;
	}
	
	/**
	 * @return  this vector minus the given one
	 */
	public Vector subtract(Vector _other)
	{
		return new Vector(this.get_head().subtract(_other.get_head()));
	}

	/**
	 * @return this vector plus the given one
	 */
	public Vector add(Vector _other)
	{
		return new Vector(this.get_head().add(_other));
	}

	/**
	 * @return this vector mult with scalar
	 */
	public Vector scale(double _scalar)
	{
		return new Vector(this._head.get_x().get()*_scalar,this._head.get_y().get()*_scalar,this._head.get_z().get()*_scalar);
	}
	
	/**
	 * @return length of this vector by square
	 */
	public double lengthSquared()
	{
		return this.get_head().get_x().get()*this.get_head().get_x().get()+this.get_head().get_y().get()*this.get_head().get_y().get()+this.get_head().get_z().get()*this.get_head().get_z().get();
	}
	
	/**
	 * @return length of this vector
	 */
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
	
	/**
	 * normalize this vector and return it fixed
	 */
	public Vector normalize()
	{
		double len=1/(this.length());
		this._head=new Point3D(this.scale(len).get_head());
		return this;
	}
	
	/**
	 * @return normalize of this vector (without normalize it self) 
	 */
	public Vector normalized()
	{
		Vector newV=new Vector(this);
		return newV.normalize();		
	}
	
	public double dotProduct(Vector _other)
	{
		return this.get_head().get_x().get()*_other.get_head().get_x().get()+this.get_head().get_y().get()*_other.get_head().get_y().get()+this.get_head().get_z().get()*_other.get_head().get_z().get();
	}
	
	public Vector crossProduct(Vector _other)
	{
		return (new Vector((this.get_head().get_y().get()*_other.get_head().get_z().get())-(this.get_head().get_z().get()*_other.get_head().get_y().get()),(this.get_head().get_z().get()*_other.get_head().get_x().get())-(this.get_head().get_x().get()*_other.get_head().get_z().get()),(this.get_head().get_x().get()*_other.get_head().get_y().get())-(this.get_head().get_y().get()*_other.get_head().get_x().get())));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (_head == null) {
			if (other._head != null)
				return false;
		} else if (!_head.equals(other._head))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "head=" + _head;
	}


}
