/**
 * 
 */
package geometries;

import primitives.*;
import java.util.List;
/**
 * @author ψημι
 *
 */
public interface Intersectable {
	 /**
    *
    * @param ray ray pointing toward a Gepmtry
    * @return List<Point3D> return values
    */
   List<Point3D> findIntersections(Ray ray);

}
