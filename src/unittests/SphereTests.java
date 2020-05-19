/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
import primitives.*;


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
		// =============== spheres will help us soon ==================
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
	
	/**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        List<Point3D> points = new LinkedList<>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                        sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
      points.clear();
        		for( GeoPoint g: sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),new Vector(3, 1, 0)))) {
        			points.add(g.getPoint());
        		}
        			
        assertEquals("Wrong number of points", 2, points.size());
        if (points.get(0).get_x().get() > points.get(1).get_x().get())
            points = List.of(points.get(1), points.get(0));
        assertEquals("Ray crosses sphere", points, List.of(p1, p2));
        

        // TC03: Ray starts inside the sphere (1 point)
        points.clear();
        for(GeoPoint g: sphere.findIntersections(new Ray(new Point3D(1,0,0), new Vector(0, 1, 0)))) {
			points.add(g.getPoint());
		}
        assertEquals("Ray's line in of sphere", List.of(new Point3D(1, 1, 0)),points);
        // TC04: Ray starts after the sphere (0 points)
        assertEquals("Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(10,10,10), new Vector(1, 1, 1))));
        
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        points.clear();
        for(GeoPoint g: sphere.findIntersections(new Ray(new Point3D(0,0,0), new Vector(1, 1, 0))))
        {
        	points.add(g.getPoint());
        }
        assertEquals("Ray starts at sphere and goes inside", List.of(new Point3D(1,1, 0)),
               points );
         // TC12: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray starts at sphere and goes outside", null,
        		sphere.findIntersections(new Ray(new Point3D(0,0,0), new Vector(-1, -1, 0))));
        // **** Group: Ray's line goes through the center
       // TC13: Ray starts before the sphere (2 points)
        points.clear();
        for(GeoPoint g: sphere.findIntersections(new Ray(new Point3D(-1,0,0), new Vector(1, 0, 0))))
        {
        	points.add(g.getPoint());        }
        assertEquals("Ray starts before the sphere", List.of(new Point3D(0, 0, 0),new Point3D(2, 0, 0)),
                points);
       // TC14: Ray starts at sphere and goes inside (1 points)
        points.clear();
        for(GeoPoint g:  sphere.findIntersections(new Ray(new Point3D(0,0,0), new Vector(1, 0, 0))))
        {
        	points.add(g.getPoint());        }
        assertEquals("Ray starts at sphere and goes inside", List.of(new Point3D(2, 0, 0)),
               points);
         // TC15: Ray starts inside (1 points)
        points.clear();
        for(GeoPoint g:  sphere.findIntersections(new Ray(new Point3D(0.5,0,0), new Vector(1, 0, 0))))
        {
        	points.add(g.getPoint());        }
        assertEquals("Ray starts inside", List.of(new Point3D(2, 0, 0)),
               points );
        // TC16: Ray starts at the center (1 points)
        points.clear();
        for(GeoPoint g:   sphere.findIntersections(new Ray(new Point3D(1,0,0), new Vector(1, 0, 0))))
        {
        	points.add(g.getPoint());        }
        assertEquals("Ray starts at the center", List.of(new Point3D(2, 0, 0)),
             points );
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertEquals("Ray starts at sphere and goes outside", null,
        		 sphere.findIntersections(new Ray(new Point3D(0,0,0), new Vector(-1, 0, 0))));
        // TC18: Ray starts after sphere (0 points)
        assertEquals("Ray starts after sphere", null,
                sphere.findIntersections(new Ray(new Point3D(4,0,0), new Vector(1,0, 0))));
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertEquals("Ray starts before the tangent point", null,
                sphere.findIntersections(new Ray(new Point3D(0.5,1,0), new Vector(1,0, 0))));
        // TC20: Ray starts at the tangent point
        assertEquals("Ray starts at the tangent point", null,
                sphere.findIntersections(new Ray(new Point3D(1,1,0), new Vector(1,0, 0))));
        // TC21: Ray starts after the tangent point
        assertEquals("Ray starts after the tangent point", null,
                sphere.findIntersections(new Ray(new Point3D(2,1,0), new Vector(1,0, 0))));
        
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertEquals("Ray's line is outside", null,
                sphere.findIntersections(new Ray(new Point3D(-0.5,0,0), new Vector(0,1, 0))));
    }

	}


