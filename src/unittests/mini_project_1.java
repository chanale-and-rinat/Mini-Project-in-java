/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
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
	/**
	 * mini project 1-with soft shadow implement
	 */
	@Test
	public void trianglesSphereWithImplement1() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(50, 100, -11000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(9000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(0,0,0), 0.1));

		scene.addGeometries( 
				
				new Plane(new Color(java.awt.Color.black), new Material(0.4, 0.3, 20000,0,0.4), //
						new Point3D(1500,1000,0), new Point3D(-1500,1000,3850), new Point3D(-1500,1000,0)), 
				new Sphere(new Color(255, 0, 0), new Material(0.8, 0.8, 200, 0,0.8), 300, new Point3D(0, 700, 900)),//RED
				new Sphere(new Color(java.awt.Color.ORANGE), new Material(0.8, 0.8, 200, 0,0.7), 200, new Point3D(-280, 800, 100)),//yellow
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.8, 0.8, 200, 0.4,0.4), 120, new Point3D(-300, 880, -600)),//blue
				new Sphere(new Color(java.awt.Color.GREEN), new Material(0.8, 0.8, 200, 0,0.7), 120, new Point3D(-500, 880, -300)),//green
				new Sphere(new Color(java.awt.Color.ORANGE), new Material(0.8, 0.8, 200, 0.4,0.4), 100, new Point3D(400, 900, -800)),//blue
				new Sphere(new Color(java.awt.Color.GREEN), new Material(0.8, 0.8, 200, 0,0.7), 100, new Point3D(550, 900, -600)),//green
				new Sphere(new Color(java.awt.Color.ORANGE), new Material(0.8, 0.8, 200, 0.4,0.4), 100, new Point3D(-700, 900, -800)),//blue
				new Sphere(new Color(java.awt.Color.GREEN), new Material(0.8, 0.8, 200, 0,0.7), 100, new Point3D(-850, 900, -600))//green		
				);
	scene.addLights(new SpotLight(new Color(1020, 400, 400), 
			new Point3D(0,800,-400), new Vector(-1,1,4).normalize(), 1,0.00001, 0.000005));
	scene.addLights(new SpotLight(new Color(20, 40, 0), 
					new Point3D(800,600,-300), new Vector(-1,1,4), 1,0.00001, 0.000005));
	scene.addLights(new SpotLight(new Color(1020, 400, 400), 
			new Point3D(-800,700,-300), new Vector(-1,1,4), 1,0.00001, 0.000005));
	scene.addLights(new DirectionalLight(new Color(java.awt.Color.darkGray),
            new Vector(-0.5,0.5,0)));
	scene.addLights(new SpotLight(new Color(java.awt.Color.darkGray), 
			new Point3D(0,0,11000), new Vector(0,0,-1).normalize(), 1,0.00001, 0.000005));
	
		ImageWriter imageWriter = new ImageWriter("mini project 1", 2500, 2500, 500, 500);
		render render = new render(imageWriter, scene);

		render.renderImage(81);
		render.writeToImage();
	}
	@Test
	public void trianglesSphereWithImplement2() {
	Scene scene = new Scene("Test scene");
	scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
	scene.set_distance(1000);
	scene.set_background(Color.BLACK);
	scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

	scene.addGeometries( //
			new Triangle(Color.BLACK, new Material(0, 0.8, 60,0,0.1), //
					new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
			new Triangle(Color.BLACK, new Material(0, 0.8, 60,0,0.1), //
					new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
			new Sphere(new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30, 0,0.3), // )
					20, new Point3D(50, -40, 100)),
			new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30, 0,0.3), // )
					17, new Point3D(20, -10, 100)),
			new Sphere(new Color(java.awt.Color.GREEN), new Material(0.5, 0.5, 30, 0,0.3), // )
					14, new Point3D(-10, 20, 100)),
			new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30, 0,0.3), // )
					11, new Point3D(-40, 50, 100)),
			new Sphere(new Color(java.awt.Color.YELLOW), new Material(0.5, 0.5, 30, 0,0.3), // )
					8, new Point3D(-62, 70, 100)),
			new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 30, 0,0.3), // )
					5, new Point3D(-82, 90, 100))

			);

	scene.addLights(new SpotLight(new Color(700, 400, 400), //
			new Point3D(40, -40, -115), new Vector(-1, 1, 4), 1, 4E-4, 2E-5,1,20));

	ImageWriter imageWriter = new ImageWriter("mini project 1b", 200, 200, 600, 600);
	render render = new render(imageWriter, scene).setMultithreading(3).setDebugPrint();

	render.renderImage(81);
	render.writeToImage();
	}

}
