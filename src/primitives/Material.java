package primitives;

public class Material {
    private double _kD;
    private double _kS;
    private double _kT;
    private double _kR;
    private int _nShininess;

    /**
  	 * @param _kD
  	 * @param _kS
  	 * @param _nShininess
  	 */
    public Material(double _kD, double _kS, int _nShininess) {
        this(_kD,_kS,_nShininess,0,0);
    }
    
    /**
	 * @param _kD
	 * @param _kS
	 * @param _nShininess
	 * @param _kT
	 * @param _kR
	 */
	public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) {
		super();
		this._kD = _kD;
		this._kS = _kS;
		this._kT = _kT;
		this._kR = _kR;
		this._nShininess = _nShininess;
	}

	/**
	 * @return the _kT
	 */
	public double get_kT() {
		return _kT;
	}

	/**
	 * @return the _kR
	 */
	public double get_kR() {
		return _kR;
	}

	/**
	 * @param material
	 */
    public Material(Material material) {
        this(material.getKd(), material.getKs(), material.getnShininess(),material.get_kT(),material.get_kR());
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