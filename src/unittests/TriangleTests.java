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
public class TriangleTests {

	@Test
	public void testFindIntersections()
	{
	Triangle triangle =new Triangle(new Point3D(0,0,1),new Point3D(1,0,0), new Point3D(-1,0,0));
    List<Point3D> points = new LinkedList<>();
	// ============ Equivalence Partitions Tests ==============

	//TC01: Inside triangle
    points.clear();
	for( GeoPoint g: triangle.findIntersections((new Ray(new Point3D(0, 2, 0.5), new Vector(0, -1, 0))))) {
		points.add(g.getPoint());
	}
	assertEquals("Ray Inside the triangle",List.of(new Point3D(0,0,0.5)),points);
	//TC02: Outside against edge
	assertEquals("Ray starts outside against edge",null,triangle.findIntersections((new Ray(new Point3D(0.5, -2, -1), new Vector(0, 1, 0)))));
	//TC03: Outside against vertex
	assertEquals("Ray starts outside against vertex",null,triangle.findIntersections((new Ray(new Point3D(1.5, -2, -0.2), new Vector(0, 1, 0)))));
	// =============== Boundary Values Tests ==================
	//TC11: the point is on edge
	assertEquals("Ray's point is on the edge",null,triangle.findIntersections((new Ray(new Point3D(0.5, -2, 0), new Vector(0, 1, 0)))));
	//TC12: the point is in vertex
	assertEquals("Ray's point is in vertex",null,triangle.findIntersections((new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0)))));
	//TC13: the point is On edge's continuation
	assertEquals("Ray's point is On edge's continuation",null,triangle.findIntersections((new Ray(new Point3D(2, -2, 0), new Vector(0, 1, 0)))));
	    }

}
