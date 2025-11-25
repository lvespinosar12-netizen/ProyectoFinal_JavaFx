package Space_Suit_Manager.modelo;

/**
 * Clase que representa una misión espacial en el sistema de gestión de trajes espaciales.
 * 
 * Esta clase encapsula la información relacionada con una misión espacial, incluyendo
 * su código identificador, nombre, objetivo de la misión y fecha de lanzamiento.
 * Proporciona acceso y modificación de estos atributos a través de getters y setters.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 */
public class MisionEspacial {

	/** Código identificador único de la misión espacial. */
	private String codigoMision;
	
	/** Nombre descriptivo de la misión espacial. */
	private String nombre;
	
	/** Objetivo principal de la misión espacial. */
	private String objetivo;
	
	/** Fecha de lanzamiento de la misión en formato de cadena de texto. */
	private String fechaLanzamiento;

	/**
	 * Constructor que inicializa una nueva misión espacial con todos sus atributos.
	 * 
	 * @param codigoMision el código identificador único de la misión
	 * @param nombre el nombre descriptivo de la misión
	 * @param objetivo el objetivo principal que se desea alcanzar en la misión
	 * @param fechaLanzamiento la fecha de lanzamiento de la misión
	 */
	public MisionEspacial(String codigoMision, String nombre, String objetivo, String fechaLanzamiento) {
		super();
		this.codigoMision = codigoMision;
		this.nombre = nombre;
		this.objetivo = objetivo;
		this.fechaLanzamiento = fechaLanzamiento;
	}

	/**
	 * Obtiene el código identificador único de la misión espacial.
	 * 
	 * @return el código de la misión
	 */
	public String getCodigoMision() {
		return codigoMision;
	}

	/**
	 * Establece el código identificador único de la misión espacial.
	 * 
	 * @param codigoMision el nuevo código de la misión
	 */
	public void setCodigoMision(String codigoMision) {
		this.codigoMision = codigoMision;
	}

	/**
	 * Obtiene el nombre descriptivo de la misión espacial.
	 * 
	 * @return el nombre de la misión
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre descriptivo de la misión espacial.
	 * 
	 * @param nombre el nuevo nombre de la misión
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el objetivo principal de la misión espacial.
	 * 
	 * @return el objetivo de la misión
	 */
	public String getObjetivo() {
		return objetivo;
	}

	/**
	 * Establece el objetivo principal de la misión espacial.
	 * 
	 * @param objetivo el nuevo objetivo de la misión
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	/**
	 * Obtiene la fecha de lanzamiento de la misión espacial.
	 * 
	 * @return la fecha de lanzamiento de la misión
	 */
	public String getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	/**
	 * Establece la fecha de lanzamiento de la misión espacial.
	 * 
	 * @param fechaLanzamiento la nueva fecha de lanzamiento de la misión
	 */
	public void setFechaLanzamiento(String fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	/**
	 * Retorna una representación en cadena de texto de la misión espacial.
	 * 
	 * @return una cadena con todos los atributos de la misión
	 */
	@Override
	public String toString() {
		return "MisionEspacial [codigoMision=" + codigoMision + ", nombre=" + nombre + ", objetivo=" + objetivo
				+ ", fechaLanzamiento=" + fechaLanzamiento + "]";
	}

}