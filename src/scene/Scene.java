/**
 * 
 */
package scene;

import elements.*;
import geometries.*;
import primitives.*;

/**
 * @author rinat
 *
 */
public class Scene {
	String _name;
	Color _background;
	AmbientLight _ambientLight;
	Geometries _geometries;
	Camera _camera;
	double _distance;
	
	/**
	 * @param _name
	 */
	public Scene(String _name) {
		super();
		this._name = _name;
		_geometries=new Geometries();
	}

	/**
	 * @return the _background
	 */
	public Color get_background() {
		return _background;
	}

	/**
	 * @param _background the _background to set
	 */
	public void set_background(Color _background) {
		this._background = _background;
	}

	/**
	 * @return the _ambientLight
	 */
	public AmbientLight get_ambientLight() {
		return _ambientLight;
	}

	/**
	 * @param _ambientLight the _ambientLight to set
	 */
	public void set_ambientLight(AmbientLight _ambientLight) {
		this._ambientLight = _ambientLight;
	}

	/**
	 * @return the _camera
	 */
	public Camera get_camera() {
		return _camera;
	}

	/**
	 * @param _camera the _camera to set
	 */
	public void set_camera(Camera _camera) {
		this._camera = _camera;
	}

	/**
	 * @return the _distance
	 */
	public double get_distance() {
		return _distance;
	}

	/**
	 * @param _distance the _distance to set
	 */
	public void set_distance(double _distance) {
		this._distance = _distance;
	}

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @return the _geometries
	 */
	public Geometries get_geometries() {
		return _geometries;
	}
	public void addGeometries(Intersectable... geometries) 
	{
		_geometries.add(geometries);
	}
	
}
