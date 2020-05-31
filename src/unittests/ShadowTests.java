package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

/**
 * Testing basic shadows
 * 
 * @author rinat
 */
public class ShadowTests {

	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 */
	@Test
	public void SphereTriangleInitial() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleInitial", 200, 200, 400, 400);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Sphere-Triangle shading - move triangle up-right
	 */
	@Test
	public void SphereTriangleMove1() {
		Scene scene = new Scene("Test scene move triangle up-right");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-60, 30, 0), new Point3D(-30, 60, 0), new Point3D(-58, 58, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("Test scene move triangle up-right", 200, 200, 400, 400);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Sphere-Triangle shading - move triangle upper-righter
	 */
	@Test
	public void SphereTriangleMove2() {
		Scene scene = new Scene("Test scene move triangle upper-righter");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-50, 20, 0), new Point3D(-20, 50, 0), new Point3D(-48, 48, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("Test scene move triangle upper-righter", 200, 200, 400, 400);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Sphere-Triangle shading 
	 */
	@Test
	public void SphereTriangleSpot1() {
		Scene scene = new Scene("Test scene no change");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("Test scene no change", 200, 200, 400, 400);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}	
	
	/**
	 * Sphere-Triangle shading - move spot closer
	 */
	@Test
	public void SphereTriangleSpot2() {
		Scene scene = new Scene("Test scene move spot closer");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-88, 88, -120), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("Test scene move spot closer", 200, 200, 400, 400);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}	
	/**
	 * Sphere-Triangle shading - move spot even more close
	 */
	@Test
	public void SphereTriangleSpot3() {
		Scene scene = new Scene("Test scene move spot even more close");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-76, 76, -70), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("Test scene move spot even more close", 200, 200, 400, 400);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}	
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere producing a shading
	 */
	@Test
	public void trianglesSphere() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0, 0.8, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0, 0.8, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), // )
						30, new Point3D(0, 0, 115)));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(40, -40, -115), new Vector(-1, 1, 4), 1, 4E-4, 2E-5));

		ImageWriter imageWriter = new ImageWriter("trianglesSphere", 200, 200, 600, 600);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

}
