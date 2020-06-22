/**
 * 
 */
package geometries;

import primitives.*;
import primitives.*;

/**
 * interface Geometry is the basic interface for all geometric objects
 * who are implementing getNormal method.
 *
 **/
public abstract class Geometry implements Intersectable {

    protected Color _emission;
    protected Material _material;
    protected Box _box;


    public Geometry(Color emission, Material material,Box box) {
        this._emission = new Color(emission);
        this._material = new Material(material);
        this._box=box;
        
    }

    public Geometry(Color _emission,Box box) {
        this(_emission, new Material(0d, 0d, 0),box);
    }

    public Geometry(Box box) {
        this(Color.BLACK,box);
    }

    public Color getEmissionLight() {
        return (_emission);
    }

    public Material getMaterial() {
        return _material;
    }

    /**
	 * @return the _box
	 */
	public Box get_box() {
		return _box;
	}

	/**
	 * @param _box the _box to set
	 */
	public void set_box(Box _box) {
		this._box = _box;
	}

	abstract public Vector getNormal(Point3D p);
	
	abstract public Point3D getPositionPoint();
}