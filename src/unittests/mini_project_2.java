package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import k_means_algorithm.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.render;
import scene.Scene;

public class mini_project_2 {

	@Test
	public void test_1() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		Triangle A=new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(-70,70,0),new Point3D(-30,70,0),new Point3D(-50,90,0));
			
		Triangle C=new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(0,20,0),new Point3D(0,90,0),new Point3D(-20,20,0));
		Triangle D=new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(-60,-20,0),new Point3D(-80,-20,0),new Point3D(-60,-80,0));
		
		/*Polygon D=new Polygon(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(-60,-20,0),new Point3D(-80,-20,0),new Point3D(-60,-80,0),new Point3D(-80,-80,0));
		Polygon C=new Polygon(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(0,20,0),new Point3D(0,90,0),new Point3D(-20,20,0),new Point3D(-20,90,0));*/
		
		Sphere B=new Sphere(new Color(153,2,44), new Material(0.5, 0.5, 30,0.5,0), // )
				20, new Point3D(-50,40,0));
		
		Sphere E=new Sphere(new Color(153,2,44), new Material(0.5, 0.5, 30,0.5,0), // )
				20, new Point3D(-30,-60,0));
		
		Triangle F=new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(60,-80,0),new Point3D(100,-80,0),new Point3D(80,-40,0));
		
		/*Geometries geos2 = new Geometries(A, B, C);
		Geometries geos3 = new Geometries(D, E);
		Geometries geos1 = new Geometries(geos2, F, geos3);
		Geometries geos=new Geometries(geos2, geos3,geos1);*/
		Geometries geos2 = new Geometries(A, B, C);
		Geometries geos3 = new Geometries(D, E);
		Geometries geos1 = new Geometries(geos2, F, geos3);
		Geometries geos=new Geometries(geos2, geos3,geos1);
		
		scene.addGeometries(geos);
	
	scene.addLights(new SpotLight(new Color(500, 250, 250), 
			new Point3D(-95,-95,300), new Vector(95,75,-185).normalize(), 1, 4E-4, 2E-5,1,25));
	scene.addLights(new SpotLight(new Color(500, 250, 250), 
					new Point3D(-100,-100,10000), new Vector(-1,-1,-0.5), 1, 4E-4, 2E-5,1,25));
	scene.addLights(new PointLight(new Color(500, 250, 250),
            new Point3D(0,-100,0),
            1, 0.0005, 0.0005));
	
		ImageWriter imageWriter = new ImageWriter("mini 2 test 1", 200, 200, 600, 600);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void test_2() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, 1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		Triangle A=new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(-70,70,0),new Point3D(-30,70,0),new Point3D(-50,90,0));
			
		Triangle C=new Triangle(new Color(java.awt.Color.GREEN), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(0,20,0),new Point3D(0,90,0),new Point3D(-20,20,0));
		Triangle D=new Triangle(new Color(java.awt.Color.YELLOW), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(-60,-20,0),new Point3D(-80,-20,0),new Point3D(-60,-80,0));
		
		/*Polygon D=new Polygon(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(-60,-20,0),new Point3D(-80,-20,0),new Point3D(-60,-80,0),new Point3D(-80,-80,0));
		Polygon C=new Polygon(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(0,20,0),new Point3D(0,90,0),new Point3D(-20,20,0),new Point3D(-20,90,0));*/
		
		Sphere B=new Sphere(new Color(153,2,44), new Material(0.5, 0.5, 30,0.5,0), // )
				20, new Point3D(-50,40,0));
		
		Sphere E=new Sphere(new Color(153,2,44), new Material(0.5, 0.5, 30,0.5,0), // )
				20, new Point3D(-30,-60,0));
		
		Triangle F=new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2),
				new Point3D(60,-80,0),new Point3D(100,-80,0),new Point3D(80,-40,0));
		
		List<Point> points=new ArrayList<Point>();
		points.add(new Point(A));
		points.add(new Point(B));
		points.add(new Point(C));
		points.add(new Point(D));
		points.add(new Point(E));
		points.add(new Point(F));
		
		KMeans kmeans = new KMeans();
        kmeans.init(points);
        kmeans.calculate();
        List<Cluster> clusters=kmeans.getClusters();
        for(Cluster c: clusters) {
        	Geometries geos = new Geometries();       	
        	for(Point p: c.getPoints()) {
        		geos.add(p.getGeometry());
        	}
        	scene.addGeometries(geos);
        }

		
		
		
		scene.addLights(new SpotLight(new Color(500, 250, 250), 
				new Point3D(-95,-95,300), new Vector(95,75,-185).normalize(), 1, 4E-4, 2E-5,1,25));
		scene.addLights(new SpotLight(new Color(500, 250, 250), 
						new Point3D(-100,-100,10000), new Vector(-1,-1,-0.5), 1, 4E-4, 2E-5,1,25));
		scene.addLights(new PointLight(new Color(500, 250, 250),
	            new Point3D(0,-100,0),
	            1, 0.0005, 0.0005));
		
			ImageWriter imageWriter = new ImageWriter("mini 2 test 2", 200, 200, 600, 600);
			render render = new render(imageWriter, scene);

			render.renderImage();
			render.writeToImage();
	}

}
