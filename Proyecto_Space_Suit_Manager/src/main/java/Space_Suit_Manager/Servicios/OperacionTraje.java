package Space_Suit_Manager.Servicios;

import Space_Suit_Manager.modelo.Traje_Espacial;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD y de serialización para gestionar trajes espaciales.
 * 
 * Esta interfaz establece el contrato que deben cumplir las clases que implementen
 * la gestión completa de trajes espaciales, incluyendo operaciones de creación, lectura,
 * actualización y eliminación (CRUD), así como funcionalidades de serialización y
 * deserialización de datos a archivos.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 * @see Traje_Espacial
 * @see Space_Suit_Manager.Servicios.ImplementacionOperacionTraje
 */
public interface OperacionTraje {

    // ========== CRUD (Operaciones obligatorias) ==========

    /**
     * Guarda un nuevo traje espacial en la colección.
     * 
     * El traje se agrega solo si no existe previamente un traje con el mismo identificador.
     * 
     * @param traje el traje espacial a guardar
     * @return true si el traje se guardó exitosamente, false si ya existe o hay error
     * 
     * @see #buscar(String)
     */
    boolean guardar(Traje_Espacial traje);

    /**
     * Retorna la lista completa de todos los trajes espaciales almacenados.
     * 
     * @return una lista con todos los trajes espaciales, o una lista vacía si no hay trajes
     */
    List<Traje_Espacial> listar();

    /**
     * Busca un traje espacial específico por su identificador.
     * 
     * @param id el identificador único del traje a buscar
     * @return el traje espacial encontrado, o null si no existe un traje con ese id
     * 
     * @see #guardar(Traje_Espacial)
     */
    Traje_Espacial buscar(String id);

    /**
     * Modifica un traje espacial existente reemplazándolo por uno nuevo.
     * 
     * El traje se actualiza solo si existe uno con el identificador especificado.
     * 
     * @param id el identificador único del traje a modificar
     * @param nuevoTraje el nuevo traje con la información actualizada
     * @return true si la modificación fue exitosa, false si el traje no existe o hay error
     * 
     * @see #buscar(String)
     */
    boolean modificar(String id, Traje_Espacial nuevoTraje);

    /**
     * Elimina un traje espacial de la colección por su identificador.
     * 
     * @param id el identificador único del traje a eliminar
     * @return true si la eliminación fue exitosa, false si el traje no existe o hay error
     * 
     * @see #buscar(String)
     */
    boolean eliminar(String id);

    // ========== Serialización (Almacenamiento en archivos) ==========

    /**
     * Serializa la colección de trajes espaciales a un archivo.
     * 
     * Este método persiste todos los trajes almacenados en memoria a un archivo
     * en la ruta y con el nombre especificados, permitiendo guardar los datos
     * para su posterior recuperación.
     * 
     * @param ruta la ruta del directorio donde se guardará el archivo
     * @param nombreArchivo el nombre del archivo donde se serializarán los datos
     * @return un mensaje con información del resultado de la operación (éxito o error)
     * 
     * @see #deserializar(String, String)
     */
    String serializar(String ruta, String nombreArchivo);

    /**
     * Deserializa una colección de trajes espaciales desde un archivo.
     * 
     * Este método carga los trajes almacenados previamente en un archivo y los
     * recupera en una lista de objetos {@link Traje_Espacial}.
     * 
     * @param ruta la ruta del directorio donde se encuentra el archivo
     * @param nombreArchivo el nombre del archivo de donde se leerán los datos
     * @return una lista con los trajes espaciales deserializados, o null si hay error o el archivo no existe
     * 
     * @see #serializar(String, String)
     */
    List<Traje_Espacial> deserializar(String ruta, String nombreArchivo);
}