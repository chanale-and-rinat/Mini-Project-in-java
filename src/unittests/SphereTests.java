/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import org.junit.Test;

import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;


/**
 * @author chanale & rinat
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// =============== vectors will help us soon ==================
		    Sphere s1 = new Sphere(4, new Point3D(0,0,0));
		    Sphere s2 = new Sphere(1, new Point3D(1,1,1));	
		 // ============ Equivalence Partitions Tests ==============
		    assertTrue(s1.getNormal(new Point3D(0,0,4)).equals(new Vector(new Point3D(0,0,1))));
		    assertTrue(s1.getNormal(new Point3D(0,0,-4)).equals(new Vector(new Point3D(0,0,-1))));
		    assertTrue(s1.getNormal(new Point3D(0,4,0)).equals(new Vector(new Point3D(0,1,0))));
		    assertTrue(s1.getNormal(new Point3D(0,-4,0)).equals(new Vector(new Point3D(0,-1,0))));
		    assertTrue(s1.getNormal(new Point3D(4,0,0)).equals(new Vector(new Point3D(1,0,0))));
		    assertTrue(s1.getNormal(new Point3D(-4,0,0)).equals(new Vector(new Point3D(-1,0,0))));
		    // =============== Boundary Values Tests ==================
		    assertEquals(s2.getNormal(new Point3D(1,1,0)),new Vector(new Point3D(0,0,-1)));
		    assertTrue(s2.getNormal(new Point3D(0,1,1)).equals(new Vector(new Point3D(-1,0,0))));
		    assertTrue(s2.getNormal(new Point3D(1,0,1)).equals(new Vector(new Point3D(0,-1,0))));
		  }
	}


