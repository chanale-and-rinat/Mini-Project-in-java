/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Cylinder;
import geometries.Tube;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author chanale & rinat
 *
 */
public class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// =============== cylinder will help us soon ==================
		Cylinder _c=new Cylinder(2,new Ray(new Point3D(0.0,0.0,0.0),new Vector(0.0,0.0,1.0)),5); 			
		// ============ Equivalence Partitions Tests ==============
		//test to point which exist on the basic1 of the cylinder
	    assertEquals("test to point which exist on the basic1 of the cylinder, faild",_c.getNormal(new Point3D(1,1,0)),(new Vector(new Point3D(0,0,1))));
		//test to point which exist on the basic2 of the cylinder
	    assertEquals("test to point which exist on the basic1 of the cylinder, faild",_c.getNormal(new Point3D(1,1,5)),(new Vector(new Point3D(0,0,1))));
	    //test to point which doesnt exist on the basics of the cylinder
	    assertEquals("test to point which doesnt exist on the basics of the cylinder, faild",_c.getNormal(new Point3D(1,0,2)),(new Vector(new Point3D(1,0,0))));
		// =============== Boundary Values Tests ==================
	    //test to point which exist both on the basic1 and the side of the cylinder
	    assertEquals(_c.getNormal(new Point3D(2,0,0)),(new Vector(new Point3D(0,0,1))));
	    //test to point which exist both on the basic2 and the side of the cylinder
	    assertEquals(_c.getNormal(new Point3D(2,0,5)),(new Vector(new Point3D(0,0,1))));

}
}
