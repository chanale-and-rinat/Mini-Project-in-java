/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author rinat
 *
 */
public class AmbientLight extends light{

	/**
	 * @param _intensity
	 */
	public AmbientLight(Color iA,double kA) {
		this._intensity = iA.scale(kA);
	}
	
}
