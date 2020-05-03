/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author rinat
 *
 */
public class Camera {
	
Point3D location;
Vector v_to;
Vector v_up;
Vector v_right;
/**
 * @return the location
 */
public Point3D getLocation() {
	return location;
}
/**
 * @return the v_to
 */
public Vector getV_to() {
	return v_to;
}
/**
 * @return the v_up
 */
public Vector getV_up() {
	return v_up;
}
/**
 * @return the v_right
 */
public Vector getV_right() {
	return v_right;
}
/**
 * @param location
 * @param v_to
 * @param v_up
 */
public Camera(Point3D location, Vector v_to, Vector v_up) {
	super();
	if(v_to.dotProduct(v_up)!=0)
		throw new IllegalArgumentException("the vector 'up' and the vector 'to' weren't orthogonals");
	this.location = location;
	this.v_to = v_to.normalize();
	this.v_up = v_up.normalize();
	this.v_right=v_to.crossProduct(v_up).normalize();
}
public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance,double screenWidth, double screenHeight)
{
	Point3D p0=this.getLocation();
	Point3D pc=p0.add(v_to.scale(screenDistance));
	Point3D Pij=pc;
	double Ry=screenHeight/nY;
	double Rx=screenWidth/nX;
	if((Rx*(j-((nX-1)/2.0)))!=0.0)
	{
		Vector steps_on_X=(this.getV_right().scale(Rx*(j-((nX-1)/2.0))));
		Pij=Pij.add(steps_on_X);
	}
	if((Ry*(i-((nY-1)/2.0)))!=0.0)
	{
		Vector steps_on_Y=(this.getV_up().scale(Ry*(i-((nY-1)/2.0))));
		Pij=Pij.add(steps_on_Y.scale(-1));
	}
	Vector Vij=Pij.subtract(p0);
	return new Ray(p0,Vij.normalize());
}




}
