package primitives;

public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;

    /**
  	 * @param _kD
  	 * @param _kS
  	 * @param _nShininess
  	 */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }
    /**
	 * @param material
	 */
    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess);
    }
    /**
	 * @return the _kD
	 */
    public double getKd() {
        return _kD;
    }
    /**
	 * @return the _kS
	 */
    public double getKs() {
        return _kS;
    }
    /**
	 * @return the _nShininess
	 */
    public int getnShininess() {
        return _nShininess;
    }
}