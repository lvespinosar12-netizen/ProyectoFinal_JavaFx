package Space_Suit_Manager.modelo;

public class MisionEspacial {
	
	private String codigoMision;
	private String nombre;
	private String objetivo;
	private String fechaLanzamiento;
	
	
	public MisionEspacial(String codigoMision, String nombre, String objetivo, String fechaLanzamiento) {
		super();
		this.codigoMision = codigoMision;
		this.nombre = nombre;
		this.objetivo = objetivo;
		this.fechaLanzamiento = fechaLanzamiento;
	}


	public String getCodigoMision() {
		return codigoMision;
	}


	public void setCodigoMision(String codigoMision) {
		this.codigoMision = codigoMision;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getObjetivo() {
		return objetivo;
	}


	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}


	public String getFechaLanzamiento() {
		return fechaLanzamiento;
	}


	public void setFechaLanzamiento(String fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}


	@Override
	public String toString() {
		return "MisionEspacial [codigoMision=" + codigoMision + ", nombre=" + nombre + ", objetivo=" + objetivo
				+ ", fechaLanzamiento=" + fechaLanzamiento + "]";
	}
	

}
