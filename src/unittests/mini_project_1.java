/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.render;
import scene.Scene;

/**
 * @author rinat
 *
 */
public class mini_project_1 {
	@Test
	public void test1() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)));
		scene.set_distance(10000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
		scene.addGeometries(
				new Plane(Color.BLACK, new Material(3, 4, 19,0.9,0), //
						new Point3D(0, -200, 0), new Vector(0, 1, 0)));
		scene.addLights(
		new SpotLight( new Color(150, 150, 250), 
				new Point3D(-1000, 2000, -3000), new Vector(0.5, 2, 1),1, 0.00001, 0.0000005));

		ImageWriter imageWriter = new ImageWriter("mini project 1-2", 200, 200, 600, 600);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();	

				//scene.setCamera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));þ
	}
	
	/**
	 * mini project 1-with soft shadow implement
	 */
	@Test
	public void trianglesSphereWithImplement() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				
				new Plane(Color.BLACK, new Material(0, 0.8, 60), //
						new Point3D(100,100,0), new Point3D(-100,100,0), new Point3D(0,0,1000000000)), //
				new Sphere(new Color(153,2,44), new Material(0.5, 0.5, 30,0.5,0), // )
						30, new Point3D(0, -20, 115)),
				new Sphere(new Color(31,36,2), new Material(0.5, 0.5, 30,0.5,1), // )
						15, new Point3D(40, -5,115)),
				new Sphere(new Color(java.awt.Color.GREEN), new Material(0.5, 0.5, 30), // )
						20, new Point3D(-34, -10, 95)),
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30,0.5,0.2), // )
						15, new Point3D(-80, -5,100)),
				new Sphere(new Color(7,87,59), new Material(0.5, 0.5, 30,0.5,0.3), // )
						10, new Point3D(-10,0,75))
				//
				
				);

		
	scene.addLights(new SpotLight(new Color(500, 250, 250), 
			new Point3D(-95,-95,300), new Vector(95,75,-185).normalize(), 1, 4E-4, 2E-5,1,25));
	scene.addLights(new SpotLight(new Color(500, 250, 250), 
					new Point3D(-100,-100,10000), new Vector(-1,-1,-0.5), 1, 4E-4, 2E-5,1,25));
	scene.addLights(new PointLight(new Color(500, 250, 250),
            new Point3D(0,-100,0),
            1, 0.0005, 0.0005));
	
		ImageWriter imageWriter = new ImageWriter("mini project 1", 200, 200, 600, 600);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

}
