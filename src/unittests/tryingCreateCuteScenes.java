package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

public class tryingCreateCuteScenes {

	@Test
	public void twoSpheres() {
		Scene scene = new Scene("Test scene");
		scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(
				new Sphere(new Color(0,0,70), new Material(0.5, 0.5, 20, 0.5, 0.5), 10, new Point3D(-10,-10,-10)),
				new Sphere(new Color(0,0,0), new Material(0.5, 0.5, 20, 1, 0.5), 20, new Point3D(0,-20,0)),
				new Plane(new Color(20,20,20), new Material(0.5,0.5, 100, 1, 0),new Point3D(2500,2500,-1000),new Point3D(-2500,2500,-1000),new Point3D(0,0,1000000)));
				//new Plane(new Color(50,50,50), new Material(0.4, 0.3, 100, 0, 0.5),new Point3D(100,100,0),new Point3D(-100,100,0),new Point3D(0,0,1000000)));
		//scene.addLights(new DirectionalLight(new Color(500, 300, 0), new Vector(1,1,-1)));
		scene.addLights(new SpotLight(new Color(500, 300, 300), new Point3D(0,-10000,0),
				new Vector(0,1,0), 1, 4E-5, 2E-7));
		scene.addLights(new SpotLight(new Color(5,5,5), //
				new Point3D(60, -50, 0), new Vector(-1,0,0), 1, 4E-5, 2E-7));
        scene.addLights(new PointLight(new Color(50,50,0), new Point3D(0,-20,0), 1, 0.00001, 0.000001));
        scene.addLights(new SpotLight(new Color(500, 250, 250),
                new Point3D(10, 10, 130), new Vector(-2, 2, 1),
                1, 0.0001, 0.000005));
        scene.addLights(new SpotLight(new Color(500, 250, 250),
                new Point3D(0,100,0), new Vector(0,-1,0),
                1, 0.0001, 0.000005));
		
		ImageWriter imageWriter = new ImageWriter("plane", 150, 150, 500, 500);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	@Test
	public void twoSpheres2() {
		Scene scene = new Scene("Test scene");
		//scene.set_camera(new Camera(new Point3D(0, 0, 10000), new Vector(0, 1, 0), new Vector(0, 0, -1)));þ
		scene.set_camera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 1,0), new Vector(0, 0,-1)));
		scene.set_distance(1000);
		scene.set_background(Color.BLACK);
		scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

		Plane plane = new Plane(new Color(0,0,0),new Material(0.5,0.5, 100, 1, 0),new Point3D(0, -200, 0), new Vector(0, 1, 0)
				);
				scene.addGeometries(plane); // floor

				scene.addLights(new SpotLight(new Color(150, 150, 250),new Point3D(-1000, 2000, -3000),new Vector(0.5, 2, 1), 1, 0.00001, 0.0000005
				));

		ImageWriter imageWriter = new ImageWriter("plane2", 150, 150, 500, 500);
		render render = new render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
}
