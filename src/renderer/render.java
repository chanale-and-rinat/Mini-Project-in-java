/**
 * 
 */
package renderer;

import java.awt.Color;
import java.util.List;

import geometries.*;
import elements.*;
import primitives.*;
import scene.*;

/**
 * @author rinat
 *
 */
public class render {
	ImageWriter _image_writer;
	Scene _scene;
	
	/**
	 * @param _image_writer
	 * @param _scene
	 */
	public render(ImageWriter _image_writer, Scene _scene) {
		super();
		this._image_writer = _image_writer;
		this._scene = _scene;
	}
	
	public void renderImage() {
		Camera camera = _scene.get_camera();
		Intersectable geometries = _scene.get_geometries();
		java.awt.Color background = _scene.get_background().getColor();
		int nX = _image_writer.getNx();
		int nY=_image_writer.getNy();
		
		// i is pixel row number and j is pixel in the row number
		for(int i=0;i<nX;i++)
		{
			for(int j=0;j<nY;j++)
			{
				Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, _scene.get_distance(),_image_writer.getWidth(), _image_writer.getHeight());
				List<Point3D> intersectionPoints = geometries.findIntersections(ray);
				if( intersectionPoints==null)
				{
					_image_writer.writePixel(j, i, background);
				}
				else
				{
					Point3D closestPoint = getClosestPoint(intersectionPoints);
					_image_writer.writePixel(j, i, calcColor(closestPoint));
				}
			}
		}
		
		
	}
	private Color calcColor(Point3D point) {
		return _scene.get_ambientLight().GetIntensity().getColor();
		}
	private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
		return _scene.get_camera().getLocation();	
	}
	public void printGrid(int k, java.awt.Color color) {
		for(int i=0;i<_image_writer.getNx();i++)
		{
			for(int j=0;j<_image_writer.getNy();j++)
			{
				if(i%k==0 || j%k==0)
				{
					_image_writer.writePixel(i, j, color);
				}
			}
		}
		_image_writer.writeToImage();
		
	}
	public void writeToImage() {
		_image_writer.writeToImage();		
	} 
	
}
