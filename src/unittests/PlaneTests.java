/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;
/**
 * @author chanale & rinat
 *
 */
public class PlaneTests {
	 /**
 	 * constractors with the ZERO point3D
 	 */
	@Test
	public void testPlane() {
		 try { // test zero vector
		        Plane pl = new Plane(new Point3D(1,2,3), new Point3D(2,4,6), new Point3D(3,6,9));
	            fail("hello");
	        } catch (Exception e) {
	        	assertTrue(""+e.getMessage(),true);
	        }
	}
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

	/**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Plane _plane = new Plane(new Point3D(1,1,0),new Vector(0,1,0));
        List<Point3D> points = new LinkedList<>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (1 points)
        points.clear();
        for(GeoPoint g:_plane.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 0)))) {
			points.add(g.getPoint());
		}
        assertEquals("Ray intersects the plane",List.of( new Point3D(1,1,0)),
        		points);

        // TC02: Ray does not intersect the plane (0 points)
        assertEquals("Ray does not intersect the plane",null,
        		_plane.findIntersections(new Ray(new Point3D(10,10,10), new Vector(1, 1, 0))));

        
        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC11: Ray included (0 points)
         assertEquals("Ray included",null,
        		_plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(1, 0, 0))));
         // TC12: Ray not included in the plane(0 points)
        assertEquals("Ray not included in the plane",null,
        		_plane.findIntersections(new Ray(new Point3D(10,10,10), new Vector(1,0, 0))));
        
        // **** Group: Ray is orthogonal to the plane
        // TC13: Ray starts before the plane (1 points)
        points.clear();
        for(GeoPoint g:_plane.findIntersections(new Ray(new Point3D(0,0,0), new Vector(0,1,0)))) {
			points.add(g.getPoint());
		}
        assertEquals("Ray starts before the plane", List.of(new Point3D(0,1,0)),
        		points);
        // TC14: Ray starts in the plane (0 points)
        assertEquals("Ray starts in the plane", null,
        		_plane.findIntersections(new Ray(new Point3D(0,1,0), new Vector(0,1,0))));
        // TC15: Ray starts after the plane (0 points)
        assertEquals("Ray starts after the plane", null,
        		_plane.findIntersections(new Ray(new Point3D(0,5,0), new Vector(0,1,0))));
     
        // **** Group: Ray is neither orthogonal nor parallel
        // TC16: Ray starts at the plane (0 points)
        assertEquals("Ray starts at the plane", null,
        		_plane.findIntersections(new Ray(new Point3D(0,1,0), new Vector(1,1, 0))));
        // TC17: Ray starts begins in the same point which appears as reference point in the plane (0 points)
        assertEquals("Ray starts begins in the same point which appears as reference point in the plane", null,
        		_plane.findIntersections(new Ray(new Point3D(1,1,0), new Vector(1,1, 0))));
    }
}
