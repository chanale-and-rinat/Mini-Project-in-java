/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Plane;
import geometries.Polygon;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author chanale & rinat
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Vector v1=new Vector(1,0,-1);
        Vector v2=new Vector(0,1,-1);
       Vector n=(v1.crossProduct(v2)).normalize();
        assertEquals("Bad normal to plane", n, pl.getNormal(new Point3D(0, 0, 1)));	}

}
