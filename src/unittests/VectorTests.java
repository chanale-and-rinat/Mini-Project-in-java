/**
 * 
 */
package unittests;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

/**
 * @author ψημι
 *
 */
public class VectorTests {
	
	// =============== vectors will help us soon ==================
	 Vector v1 = new Vector(1, 2, 3);
     Vector v2 = new Vector(-2, -4, -6);
     Vector v3 = new Vector(0, 3, -2);
     
     
  // =============== try to input the zero vector in all the constractors ==================
     /**
 	 * constractors with the ZERO point3D
 	 */
	@Test
	public void testVectorPoint3D() {
		 try { // test zero vector
	            new Vector(new Point3D(0.0, 0.0, 0.0));
	            fail("ERROR: zero vector does not throw an exception");
	        } catch (Exception e) {}
	}

	 /**
 	 * constractors with 3 coordinates
 	 */
	@Test
	public void testVectorCoordinateCoordinateCoordinate() {
		 try { // test zero vector
	            new Vector(new Coordinate(0.0),new Coordinate(0.0),new Coordinate(0.0));
	            fail("ERROR: zero vector does not throw an exception");
	        } catch (Exception e) {}
	}

	 /**
 	 * constractors with 3 double numbers
 	 */
	@Test
	public void testVectorDoubleDoubleDouble() {
		 try { // test zero vector
	            new Vector(0.0,0.0,0.0);
	            fail("ERROR: zero vector does not throw an exception");
	        } catch (Exception e) {}
	}
	/**
	 * Test to substract vector from vector
	 */
	@Test
	public void testSubtract() {
		assertEquals("ERROR: Point - Point does not work correctly",new Vector(1, 1, 1),new Vector(2, 3, 4).subtract(v1));
	}

	/**
	 * Test to add vector to vector
	 */
	@Test
	public void testAdd() {
		// Test operations with vectors
        assertEquals("ERROR: Point + Vector does not work correctly",v1.add(v2),new Vector(-1, -2, -3));
	}

	/**
	 * Test to mult scale with vector
	 */
	@Test
	public void testScale() {
		 assertEquals("ERROR: Scale() doesn't work well",v1.scale(-2),v2);	       
	}

	/**
	 * Test to get the length square of vector
	 */
	@Test
	public void testLengthSquared() {
		 assertTrue("ERROR: lengthSquared() wrong value",isZero(v1.lengthSquared() - 14));	       
	}

	/**
	 * Test to get the length of vector
	 */
	@Test
	public void testLength() {
		 assertTrue("ERROR: length() wrong value",isZero(new Vector(0, 3, 4).length() - 5));	       
	}

	/**
	 * Test to normalize vector
	 */
	@Test
	public void testNormalize() {
        // test vector normalization vs vector length and cross-product
        Vector vCopy = new Vector(v1);
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals("ERROR: normalize() function creates a new vector",vCopy,vCopyNormalize);   
        assertTrue("ERROR: normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));
	}

	/**
	 * Test to get normalize vector from given vector
	 */
	@Test
	public void testNormalized() {
		  Vector u = v1.normalized();
		  assertNotSame("ERROR: normalizated() function does not create a new vector",u,v1);
	}

	/**
	 * Test for dot product
	 */
	@Test
	public void testDotProduct() {	
		// =============== Boundary Values Test ==================
		// test Dot-Product for orthogonal vectors
		assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero",isZero(v1.dotProduct(v3)));
		// test Dot-Product for regular vectors
		assertTrue("ERROR: dotProduct() wrong value",isZero(v1.dotProduct(v2) + 28));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		// ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

}
