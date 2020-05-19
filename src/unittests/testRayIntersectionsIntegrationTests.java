/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import elements.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * @author rinat
 *
 */
public class testRayIntersectionsIntegrationTests {

	@Test
	//test for sphere
	public void CameraRaySphereIntegration()
	{
		Camera cam1=new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
		Camera cam2=new Camera(new Point3D(0,0,-0.5),new Vector(0,0,1),new Vector(0,-1,0));
		Sphere sphere;
		List<GeoPoint> intersactions;
		int count;
		
		//TC01: small sphere 2 points
		sphere=new Sphere(1,new Point3D(0,0,3));
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersactions=sphere.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersactions!=null)
					count+=intersactions.size();
			}
		assertEquals("Wrong amount of intersactions",2,count);
		//TC02: big sphere 18 points, 2 for each fixel
				sphere=new Sphere(2.5,new Point3D(0,0,2.5));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersactions=sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(intersactions!=null)
							count+=intersactions.size();
					}
				assertEquals("Wrong amount of intersactions",18,count);
		//TC03: medium sphere 10 points, not all the fixels intersact the sphere
		sphere=new Sphere(2,new Point3D(0,0,2));
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersactions=sphere.findIntersections(cam2.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersactions!=null)
					count+=intersactions.size();
			}
		assertEquals("Wrong amount of intersactions",10,count);	
		//TC04:the view plane inside the sphere, 9 intersaction points
				sphere=new Sphere(4,new Point3D(0,0,2));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersactions=sphere.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(intersactions!=null)
							count+=intersactions.size();
					}
				assertEquals("Wrong amount of intersactions",9,count);	
		//TC05:the view plane after the sphere, 0 intersaction points
		sphere=new Sphere(0.5,new Point3D(0,0,-1));
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
				{
					intersactions=sphere.findIntersections(cam1.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
					if(intersactions!=null)
						count+=intersactions.size();
				}
		assertEquals("Wrong amount of intersactions",0,count);	
				
		
	}
	
	@Test
	//test for plane
	public void CameraRayPlaneIntegration()
	{
		Camera cam=new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
		Plane plane;
		List<GeoPoint> intersactions;
		int count;
		
		//TC01: the plane parallel to the view plane, 9 intersaction points
		plane=new Plane(new Point3D(0,0,2),cam.getV_to());
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersactions=plane.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersactions!=null)
					count+=intersactions.size();
			}
		assertEquals("Wrong amount of intersactions",9,count);
		//TC02: the plane slant to the view plane, 9 intersaction points
				plane=new Plane(new Point3D(0,0,10),new Vector(0,0.5,-1));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersactions=plane.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(intersactions!=null)
							count+=intersactions.size();
					}
		assertEquals("Wrong amount of intersactions",9,count);
		//TC03: the plane slant a lot to the view plane, 6 intersaction points
		plane=new Plane(new Point3D(0,0,10),new Vector(0,1,1));
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersactions=plane.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersactions!=null)
					count+=intersactions.size();
			}
		assertEquals("Wrong amount of intersactions",6,count);				
				
		
	}
	@Test
	//test for triangle
	public void CameraRayPTriangleIntegration()
	{
		Camera cam=new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
		Triangle triangle;
		List<GeoPoint> intersactions;
		int count;
		
		//TC01: the small triangle parallel to the view plane,1 intersaction points
		triangle=new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
		count=0;
		for(int i=0;i<3;++i)
			for(int j=0;j<3;++j)
			{
				intersactions=triangle.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
				if(intersactions!=null)
					count+=intersactions.size();
			}
		assertEquals("Wrong amount of intersactions",1,count);
		
		//TC01: the high triangle parallel to the view plane,2 intersaction points
				triangle=new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
				count=0;
				for(int i=0;i<3;++i)
					for(int j=0;j<3;++j)
					{
						intersactions=triangle.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
						if(intersactions!=null)
							count+=intersactions.size();
					}
				assertEquals("Wrong amount of intersactions",2,count);
				
	}

}
