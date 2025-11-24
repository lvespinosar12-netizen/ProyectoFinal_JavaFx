package Space_Suit_Manager.modelo;

public class TrajeExploracion extends Traje_Espacial {
	
	private String tipoTerreno;
	private double nivelPresion;
	
	
	public TrajeExploracion(String id_traje, String talla, double peso_Soportado, String fecha_inspeccion,
			String tipoTerreno, double nivelPresion) {
		super(id_traje, talla, peso_Soportado, fecha_inspeccion);
		this.tipoTerreno = tipoTerreno;
		this.nivelPresion = nivelPresion;
	}


	public String getTipoTerreno() {
		return tipoTerreno;
	}


	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}


	public double getNivelPresion() {
		return nivelPresion;
	}


	public void setNivelPresion(double nivelPresion) {
		this.nivelPresion = nivelPresion;
	}


	@Override
	public String toString() {
		return "TrajeExploracion [tipoTerreno=" + tipoTerreno + ", nivelPresion=" + nivelPresion + "]";
	}

}
