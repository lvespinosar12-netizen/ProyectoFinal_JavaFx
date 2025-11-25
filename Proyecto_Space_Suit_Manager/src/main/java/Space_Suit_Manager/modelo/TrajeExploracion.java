package Space_Suit_Manager.modelo;

/**
 * Clase que representa un traje de exploración espacial.
 * 
 * Esta clase extiende {@link Traje_Espacial} y añade funcionalidades específicas
 * para trajes utilizados en misiones de exploración del espacio exterior.
 * Hereda todas las propiedades de un traje espacial estándar e incluye información
 * adicional sobre el tipo de terreno a explorar y el nivel de presión que puede soportar.
 * 
 * Los trajes de exploración están diseñados para resistir condiciones extremas en
 * diferentes terrenos planetarios y mantener niveles de presión óptimos para la supervivencia
 * del astronauta.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 * @see Traje_Espacial
 */
public class TrajeExploracion extends Traje_Espacial {

	/** Tipo de terreno o planeta para el cual está diseñado este traje de exploración. */
	private String tipoTerreno;
	
	/** Nivel de presión que puede soportar el traje en atmósferas o unidades de presión estándar. */
	private double nivelPresion;

	/**
	 * Constructor que inicializa un nuevo traje de exploración con todos sus atributos.
	 * 
	 * @param id_traje el identificador único del traje
	 * @param talla la talla del traje espacial
	 * @param peso_Soportado el peso máximo que puede soportar el traje en kilogramos
	 * @param fecha_inspeccion la fecha de la última inspección del traje
	 * @param tipoTerreno el tipo de terreno o planeta para el cual está diseñado este traje
	 * @param nivelPresion el nivel de presión que puede soportar el traje
	 */
	public TrajeExploracion(String id_traje, String talla, double peso_Soportado, String fecha_inspeccion,
			String tipoTerreno, double nivelPresion) {
		super(id_traje, talla, peso_Soportado, fecha_inspeccion);
		this.tipoTerreno = tipoTerreno;
		this.nivelPresion = nivelPresion;
	}

	/**
	 * Obtiene el tipo de terreno o planeta para el cual está diseñado este traje.
	 * 
	 * @return el tipo de terreno
	 */
	public String getTipoTerreno() {
		return tipoTerreno;
	}

	/**
	 * Establece el tipo de terreno o planeta para el cual está diseñado este traje.
	 * 
	 * @param tipoTerreno el nuevo tipo de terreno
	 */
	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}

	/**
	 * Obtiene el nivel de presión que puede soportar este traje de exploración.
	 * 
	 * @return el nivel de presión en unidades estándar
	 */
	public double getNivelPresion() {
		return nivelPresion;
	}

	/**
	 * Establece el nivel de presión que puede soportar este traje de exploración.
	 * 
	 * @param nivelPresion el nuevo nivel de presión
	 */
	public void setNivelPresion(double nivelPresion) {
		this.nivelPresion = nivelPresion;
	}

	/**
	 * Retorna una representación en cadena de texto del traje de exploración.
	 * 
	 * @return una cadena con el tipo de terreno y el nivel de presión del traje
	 */
	@Override
	public String toString() {
		return "TrajeExploracion [tipoTerreno=" + tipoTerreno + ", nivelPresion=" + nivelPresion + "]";
	}

}