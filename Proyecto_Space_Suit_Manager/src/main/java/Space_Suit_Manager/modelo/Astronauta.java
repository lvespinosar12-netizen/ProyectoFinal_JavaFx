package Space_Suit_Manager.modelo;

/**
 * Clase que representa un astronauta en el sistema de gestión de trajes espaciales.
 * 
 * Esta clase encapsula la información relacionada con un astronauta, incluyendo
 * su identificador único, nombre, rango militar y años de experiencia. 
 * Proporciona acceso y modificación de estos atributos a través de getters y setters.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 */
public class Astronauta {

	/** Identificador único del astronauta. */
	private String id;
	
	/** Nombre completo del astronauta. */
	private String nombre;
	
	/** Rango o grado militar del astronauta. */
	private String rango;
	
	/** Años de experiencia en misiones espaciales del astronauta. */
	private int experiencia;

	/**
	 * Constructor que inicializa un nuevo astronauta con todos sus atributos.
	 * 
	 * @param id el identificador único del astronauta
	 * @param nombre el nombre completo del astronauta
	 * @param rango el rango o grado militar del astronauta
	 * @param experiencia los años de experiencia en misiones espaciales
	 */
	public Astronauta(String id, String nombre, String rango, int experiencia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.rango = rango;
		this.experiencia = experiencia;
	}

	/**
	 * Obtiene el identificador único del astronauta.
	 * 
	 * @return el id del astronauta
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece el identificador único del astronauta.
	 * 
	 * @param id el nuevo id del astronauta
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre completo del astronauta.
	 * 
	 * @return el nombre del astronauta
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre completo del astronauta.
	 * 
	 * @param nombre el nuevo nombre del astronauta
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el rango o grado militar del astronauta.
	 * 
	 * @return el rango del astronauta
	 */
	public String getRango() {
		return rango;
	}

	/**
	 * Establece el rango o grado militar del astronauta.
	 * 
	 * @param rango el nuevo rango del astronauta
	 */
	public void setRango(String rango) {
		this.rango = rango;
	}

	/**
	 * Obtiene los años de experiencia en misiones espaciales del astronauta.
	 * 
	 * @return los años de experiencia del astronauta
	 */
	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * Establece los años de experiencia en misiones espaciales del astronauta.
	 * 
	 * @param experiencia los nuevos años de experiencia del astronauta
	 */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * Retorna una representación en cadena de texto del astronauta.
	 * 
	 * @return una cadena con todos los atributos del astronauta
	 */
	@Override
	public String toString() {
		return "Astronauta [id=" + id + ", nombre=" + nombre + ", rango=" + rango + ", experiencia=" + experiencia
				+ "]";
	}

}
