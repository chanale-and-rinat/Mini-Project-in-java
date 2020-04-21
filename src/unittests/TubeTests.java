/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Ray;
import geometries.Tube;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author chanale & rinat
 *  
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
        Tube tube = new Tube(1.0, new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0)));
        assertEquals("Bad normal to tube", new Vector(0, 0, 1), tube.getNormal(new Point3D(0, 0.5, 2)));
		
		// =============== tubes will help us soon ==================
		Tube t1 = new Tube(4,new Ray(new Point3D(0,0,0),new Vector(new Point3D(0,0,1))));
		Tube t2 = new Tube(1,new Ray(new Point3D(1,1,1),new Vector(new Point3D(0,0,1))));
		
		// ============ Equivalence Partitions Tests ==============
	    assertEquals(t1.getNormal(new Point3D(0,4,0)),(new Vector(new Point3D(0,1,0))));
	    assertTrue(t1.getNormal(new Point3D(0,-4,0)).equals(new Vector(new Point3D(0,-1,0))));
	    assertTrue(t1.getNormal(new Point3D(4,0,0)).equals(new Vector(new Point3D(1,0,0))));
	    assertTrue(t1.getNormal(new Point3D(-4,0,0)).equals(new Vector(new Point3D(-1,0,0))));
	    assertEquals(t2.getNormal(new Point3D(2,1,2)),new Vector(new Point3D(1,0,0)));
        // =============== Boundary Values Tests ==================
	    assertEquals(t2.getNormal(new Point3D(0,1,1)),(new Vector(new Point3D(-1,0,0))));
	  	}
	
	

}
