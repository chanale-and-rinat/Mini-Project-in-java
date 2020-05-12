/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author rinat
 *
 */
public class AmbientLight {
	Color _intensity;

	/**
	 * @return the _intensity
	 */
	public Color GetIntensity() {
		return _intensity;
	}

	/**
	 * @param _intensity
	 */
	public AmbientLight(Color iA,double kA) {
		super();
		this._intensity = iA.scale(kA);
	}
	
}
