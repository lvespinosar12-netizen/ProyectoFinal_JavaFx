package Space_Suit_Manager.modelo;

/**
 * Clase que representa un traje de entrenamiento espacial.
 * 
 * Esta clase extiende {@link Traje_Espacial} y añade funcionalidades específicas
 * para trajes utilizados en simuladores de entrenamiento. Hereda todas las propiedades
 * de un traje espacial estándar e incluye información adicional sobre el tipo de
 * simulador y material del traje.
 * 
 * Los trajes de entrenamiento son versiones especializadas diseñadas para preparar
 * astronautas en ambientes controlados antes de misiones reales.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 * @see Traje_Espacial
 */
public class TrajeEntrenamiento extends Traje_Espacial {

	/** Tipo de simulador para el cual está diseñado este traje de entrenamiento. */
	private String tipoSimulador;
	
	/** Material del que está fabricado el traje de entrenamiento. */
	private String material;

	/**
	 * Constructor que inicializa un nuevo traje de entrenamiento con todos sus atributos.
	 * 
	 * @param id_traje el identificador único del traje
	 * @param talla la talla del traje espacial
	 * @param peso_Soportado el peso máximo que puede soportar el traje en kilogramos
	 * @param fecha_inspeccion la fecha de la última inspección del traje
	 * @param tipoSimulador el tipo de simulador para el cual está diseñado este traje
	 * @param material el material de fabricación del traje
	 */
	public TrajeEntrenamiento(String id_traje, String talla, double peso_Soportado, String fecha_inspeccion,
			String tipoSimulador, String material) {
		super(id_traje, talla, peso_Soportado, fecha_inspeccion);
		this.tipoSimulador = tipoSimulador;
		this.material = material;
	}

	/**
	 * Obtiene el tipo de simulador para el cual está diseñado este traje.
	 * 
	 * @return el tipo de simulador
	 */
	public String getTipoSimulador() {
		return tipoSimulador;
	}

	/**
	 * Establece el tipo de simulador para el cual está diseñado este traje.
	 * 
	 * @param tipoSimulador el nuevo tipo de simulador
	 */
	public void setTipoSimulador(String tipoSimulador) {
		this.tipoSimulador = tipoSimulador;
	}

	/**
	 * Obtiene el material de fabricación del traje de entrenamiento.
	 * 
	 * @return el material del traje
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * Establece el material de fabricación del traje de entrenamiento.
	 * 
	 * @param material el nuevo material del traje
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * Retorna una representación en cadena de texto del traje de entrenamiento.
	 * 
	 * @return una cadena con el tipo de simulador y el material del traje
	 */
	@Override
	public String toString() {
		return "TrajeEntrenamiento [tipoSimulador=" + tipoSimulador + ", material=" + material + "]";
	}

}