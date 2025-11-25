package Space_Suit_Manager.modelo;

import java.io.Serializable;

/**
 * Clase que representa un traje espacial en el sistema de gestión de trajes espaciales.
 * 
 * Esta clase encapsula la información relacionada con un traje espacial, incluyendo
 * su identificador único, talla, peso soportado y fecha de inspección.
 * Implementa la interfaz {@link Serializable} para permitir la persistencia de objetos
 * a través de serialización y deserialización.
 * 
 * Los atributos son protegidos para permitir el acceso mediante reflexión desde
 * otros paquetes, especialmente desde {@link PanelDeControl.servicios.ImplementacionOperacionTraje}.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 * @see Serializable
 * @see PanelDeControl.servicios.ImplementacionOperacionTraje
 */
public class Traje_Espacial implements Serializable {

    /** Identificador de serialización para control de versiones de la clase. */
    private static final long serialVersionUID = 1L;

    /** Identificador único del traje espacial. */
    protected String idTraje;
    
    /** Talla del traje espacial (ej: pequeña, mediana, grande). */
    protected String talla;
    
    /** Peso máximo que puede soportar el traje espacial en kilogramos. */
    protected double pesoSoportado;
    
    /** Fecha de la última inspección de mantenimiento del traje. */
    protected String fechaInspeccion;

    /**
     * Constructor que inicializa un nuevo traje espacial con todos sus atributos.
     * 
     * @param idTraje el identificador único del traje
     * @param talla la talla del traje espacial
     * @param pesoSoportado el peso máximo que puede soportar el traje en kilogramos
     * @param fechaInspeccion la fecha de la última inspección del traje
     */
    public Traje_Espacial(String idTraje, String talla, double pesoSoportado, String fechaInspeccion) {
        this.idTraje = idTraje;
        this.talla = talla;
        this.pesoSoportado = pesoSoportado;
        this.fechaInspeccion = fechaInspeccion;
    }

    /**
     * Obtiene el identificador único del traje espacial.
     * 
     * @return el id del traje
     */
    public String getIdTraje() {
        return idTraje;
    }

    /**
     * Obtiene la talla del traje espacial.
     * 
     * @return la talla del traje
     */
    public String getTalla() {
        return talla;
    }

    /**
     * Obtiene el peso máximo que puede soportar el traje espacial.
     * 
     * @return el peso soportado en kilogramos
     */
    public double getPesoSoportado() {
        return pesoSoportado;
    }

    /**
     * Obtiene la fecha de la última inspección de mantenimiento del traje.
     * 
     * @return la fecha de inspección
     */
    public String getFechaInspeccion() {
        return fechaInspeccion;
    }
}