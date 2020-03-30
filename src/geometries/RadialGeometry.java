/**
 * 
 */
package geometries;

/**
 * @author ψημι
 *
 */
public abstract class RadialGeometry implements Geometry{

	double _radius;

	/**
	 * @param _radius
	 */
	public RadialGeometry(double _radius) {
		super();
		this._radius = _radius;
	}

	/**
	 * @param _other
	 */
	public RadialGeometry(RadialGeometry _other) {
		super();
		// TODO Auto-generated constructor stub
		this._radius=_other.get_radius();	
	}
	
	/**
	 * @return the _radius
	 */
	public double get_radius() {
		return _radius;
	}
	
}
