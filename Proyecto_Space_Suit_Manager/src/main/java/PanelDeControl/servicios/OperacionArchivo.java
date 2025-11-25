package PanelDeControl.servicios;

import Space_Suit_Manager.modelo.Traje_Espacial;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD y de serialización para trajes espaciales.
 * 
 * Esta interfaz especifica el contrato que deben cumplir las clases que implementen
 * la gestión de trajes espaciales, incluyendo operaciones de creación, lectura,
 * actualización y eliminación (CRUD), así como funcionalidades de serialización y
 * deserialización de datos a archivos.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 * @see Traje_Espacial
 */
public interface OperacionArchivo {

    // ========== CRUD (Operaciones obligatorias) ==========

    /**
     * Guarda un nuevo traje espacial en el sistema.
     * 
     * Esta operación debe validar que el traje no exista previamente antes de guardarlo.
     * 
     * @param traje el traje espacial a guardar
     * @return true si el traje se guardó exitosamente, false en caso contrario
     */
    boolean guardar(Traje_Espacial traje);

    /**
     * Retorna la lista completa de todos los trajes espaciales almacenados.
     * 
     * @return una lista con todos los trajes espaciales, o una lista vacía si no hay trajes
     */
    List<Traje_Espacial> listar();

    /**
     * Busca un traje espacial específico por su código identificador.
     * 
     * @param codigo el código identificador del traje a buscar
     * @return el traje espacial encontrado, o null si no existe un traje con ese código
     */
    Traje_Espacial buscar(String codigo);

    /**
     * Modifica un traje espacial existente reemplazándolo por uno nuevo.
     * 
     * @param codigo el código identificador del traje a modificar
     * @param nuevoTraje el nuevo traje con la información actualizada
     * @return true si la modificación fue exitosa, false si el traje no existe o hay error
     */
    boolean modificar(String codigo, Traje_Espacial nuevoTraje);

    /**
     * Elimina un traje espacial del sistema por su código identificador.
     * 
     * @param codigo el código identificador del traje a eliminar
     * @return true si la eliminación fue exitosa, false si el traje no existe o hay error
     */
    boolean eliminar(String codigo);

    // ========== Serialización (Almacenamiento en archivos) ==========

    /**
     * Serializa la colección de trajes espaciales a un archivo.
     * 
     * Este método persiste todos los trajes almacenados en memoria a un archivo
     * en la ruta y con el nombre especificados.
     * 
     * @param ruta la ruta del directorio donde se guardará el archivo
     * @param nombreArchivo el nombre del archivo donde se serializarán los datos
     * @return un mensaje indicando el resultado de la operación (éxito o error)
     */
    String serializar(String ruta, String nombreArchivo);

    /**
     * Deserializa una colección de trajes espaciales desde un archivo.
     * 
     * Este método carga los trajes almacenados previamente en un archivo y los
     * recupera en un arreglo de {@link Traje_Espacial}.
     * 
     * @param ruta la ruta del directorio donde se encuentra el archivo
     * @param nombreArchivo el nombre del archivo de donde se leerán los datos
     * @return un arreglo con los trajes espaciales deserializados, o null si hay error
     */
    List<Traje_Espacial> deserializar(String ruta, String nombreArchivo);

}