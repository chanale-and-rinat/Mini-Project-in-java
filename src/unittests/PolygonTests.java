/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
    	//Plane _plane = new Plane(new Point3D(1,1,0),new Vector(0,1,0));
        Polygon pl = new Polygon(new Point3D(0, 0, 0), new Point3D(0, 3, 0),new Point3D(6,3,0), new Point3D(5,0,0));
        // ============ Equivalence Partitions Tests ==============

       // TC01: Ray intersects the polygon (1 points)
        assertEquals("Ray intersects the polygon",List.of( new Point3D(1,1,0)),
        		pl.findIntersections(new Ray(new Point3D(0,0,-1), new Vector(1, 1, 1))));
      
    // TC02: Ray does not intersect the polygon (0 points)
        assertEquals("Ray does not intersect the polygon",null,
        		pl.findIntersections(new Ray(new Point3D(10,10,10), new Vector(1, 1, 0))));

        
        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the polygon
        // TC11: Ray included (0 points)
         assertEquals("Ray included",null,
        		pl.findIntersections(new Ray(new Point3D(0,0,0), new Vector(0, 1, 0))));
         // TC12: Ray not included in the polygon(0 points)
        assertEquals("Ray not included in the polygon",null,
        		pl.findIntersections(new Ray(new Point3D(0,0,-1), new Vector(0,1, 0))));
        
        // **** Group: Ray is orthogonal to the polygon
        // TC13: Ray starts before the polygon (1 points)
        assertEquals("Ray starts before the polygon", List.of(new Point3D(1,1,0)),
        		pl.findIntersections(new Ray(new Point3D(1,1,-1), new Vector(0,0,1))));
        // TC14: Ray starts in the polygon (0 points)
        assertEquals("Ray starts in the polygon", null,
        		pl.findIntersections(new Ray(new Point3D(1,1,0), new Vector(0,0,1))));
        // TC15: Ray starts after the polygon (0 points)
        assertEquals("Ray starts after the polygon", null,
        		pl.findIntersections(new Ray(new Point3D(1,1,1), new Vector(0,0,1))));
     
        // **** Group: Ray is neither orthogonal nor parallel
        // TC16: Ray starts at the polygon (0 points)
        assertEquals("Ray starts at the polygon", null,
        		pl.findIntersections(new Ray(new Point3D(1,1,0), new Vector(1,1, 0))));
     // TC17: Ray intersects the polygon in vertex (0 points)
        assertEquals("Ray intersects the polygon",/*List.of( new Point3D(1,1,0))*/null,
        		pl.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 0))));
    }
    

}
