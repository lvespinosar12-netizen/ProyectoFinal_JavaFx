package Space_Suit_Manager.modelo;

public class TrajeEntrenamiento extends Traje_Espacial {
	
	private String tipoSimulador;
	private String material;
	
	
	public TrajeEntrenamiento(String id_traje, String talla, double peso_Soportado, String fecha_inspeccion,
			String tipoSimulador, String material) {
		super(id_traje, talla, peso_Soportado, fecha_inspeccion);
		this.tipoSimulador = tipoSimulador;
		this.material = material;
	}


	public String getTipoSimulador() {
		return tipoSimulador;
	}


	public void setTipoSimulador(String tipoSimulador) {
		this.tipoSimulador = tipoSimulador;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	@Override
	public String toString() {
		return "TrajeEntrenamiento [tipoSimulador=" + tipoSimulador + ", material=" + material + "]";
	}

}
