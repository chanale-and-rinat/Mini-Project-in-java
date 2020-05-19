/**
 * 
 */
package geometries;

import primitives.*;

/**
 * @author ψημι
 *
 */
public abstract class RadialGeometry extends Geometry{

	double _radius;
    /**
     * constructor for a new extended  RadialGeometry object.
     *
     * @param radius   the radius of the RadialGeometry
     * @param material the material of the RadialGeometry
     * @throws Exception in case of negative or zero radius
     */
    public RadialGeometry(Color emissionLight, double radius, Material material) {
        super(emissionLight, material);
        _radius=radius;
    }

    public RadialGeometry(Color emissionLight, double radius) {
        super(emissionLight);
        _radius=radius;
    }
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
