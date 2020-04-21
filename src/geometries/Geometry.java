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
public interface Geometry extends Intersectable{
	
	public Vector getNormal(Point3D _p);

}
