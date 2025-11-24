package Space_Suit_Manager.modelo;

public class Astronauta {
	
	private String id;
	private String nombre;
	private String rango;
	private int experiencia;
	
	public Astronauta(String id, String nombre, String rango, int experiencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.rango = rango;
		this.experiencia = experiencia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	@Override
	public String toString() {
		return "Astronauta [id=" + id + ", nombre=" + nombre + ", rango=" + rango + ", experiencia=" + experiencia
				+ "]";
	}
	

}
