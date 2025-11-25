package PanelDeControl.servicios;

import Space_Suit_Manager.modelo.Traje_Espacial;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementación de las operaciones CRUD y serialización para trajes espaciales.
 * 
 * Esta clase implementa la interfaz {@link OperacionTraje} y proporciona funcionalidades
 * para gestionar una colección de trajes espaciales, incluyendo operaciones de creación,
 * lectura, actualización, eliminación, así como serialización y deserialización a archivos.
 * 
 * La clase utiliza reflexión para acceder a campos protegidos de la clase {@link Traje_Espacial},
 * permitiendo la lectura de atributos como id, talla, peso y fecha de inspección.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 * @see OperacionTraje
 * @see Traje_Espacial
 */
public class ImplementacionOperacionTraje implements OperacionTraje {

    /** Lista que almacena todos los trajes espaciales en memoria. */
    private List<Traje_Espacial> trajes;
    
    /** Ruta por defecto para guardar y cargar archivos serializados. */
    private final String RUTA_DEFECTO = "."; // carpeta actual por defecto

    /**
     * Constructor que inicializa la lista de trajes como un ArrayList vacío.
     */
    public ImplementacionOperacionTraje() {
        this.trajes = new ArrayList<>();
    }

    // ---------- CRUD ----------

    /**
     * Guarda un nuevo traje espacial en la colección.
     * 
     * El traje se añade solo si no existe uno con el mismo id.
     * 
     * @param traje el traje espacial a guardar
     * @return true si el traje se guardó exitosamente, false si ya existe o si hay error
     * 
     * @see #buscar(String)
     * @see #obtenerId(Traje_Espacial)
     */
    @Override
    public boolean guardar(Traje_Espacial traje) {
        String id = obtenerId(traje);
        if (id == null) return false;
        if (buscar(id) != null) return false; // ya existe
        return trajes.add(traje);
    }

    /**
     * Retorna una copia de la lista completa de trajes espaciales.
     * 
     * @return una nueva lista con todos los trajes, o una lista vacía si no hay trajes
     */
    @Override
    public List<Traje_Espacial> listar() {
        return new ArrayList<>(trajes);
    }

    /**
     * Busca un traje espacial por su identificador.
     * 
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param id el identificador del traje a buscar
     * @return el traje encontrado, o null si no existe o si el id es null
     */
    @Override
    public Traje_Espacial buscar(String id) {
        if (id == null) return null;
        for (Traje_Espacial t : trajes) {
            String tid = obtenerId(t);
            if (tid != null && tid.equalsIgnoreCase(id)) return t;
        }
        return null;
    }

    /**
     * Modifica un traje espacial existente reemplazándolo por uno nuevo.
     * 
     * El traje se actualiza si existe uno con el id especificado.
     * 
     * @param id el identificador del traje a modificar
     * @param nuevoTraje el nuevo traje con la información actualizada
     * @return true si la modificación fue exitosa, false si el traje no existe o parámetros son null
     * 
     * @see #buscar(String)
     * @see #obtenerId(Traje_Espacial)
     */
    @Override
    public boolean modificar(String id, Traje_Espacial nuevoTraje) {
        if (id == null || nuevoTraje == null) return false;
        for (int i = 0; i < trajes.size(); i++) {
            String tid = obtenerId(trajes.get(i));
            if (tid != null && tid.equalsIgnoreCase(id)) {
                trajes.set(i, nuevoTraje);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un traje espacial de la colección por su identificador.
     * 
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param id el identificador del traje a eliminar
     * @return true si el traje fue eliminado exitosamente, false si no existe o id es null
     * 
     * @see #buscar(String)
     */
    @Override
    public boolean eliminar(String id) {
        if (id == null) return false;
        return trajes.removeIf(t -> {
            String tid = obtenerId(t);
            return (tid != null && tid.equalsIgnoreCase(id));
        });
    }

    // ---------- SERIALIZACIÓN ----------

    /**
     * Serializa la lista de trajes espaciales a un archivo.
     * 
     * Si la ruta está vacía, se utiliza la ruta por defecto (carpeta actual).
     * Si el nombre del archivo está vacío, se utiliza "trajes_espaciales.dat".
     * 
     * @param ruta la ruta del directorio donde se guardará el archivo (puede ser null)
     * @param nombreArchivo el nombre del archivo a crear (puede ser null)
     * @return un mensaje indicando éxito con la ruta absoluta, o un mensaje de error
     * 
     * @see #RUTA_DEFECTO
     */
    @Override
    public String serializar(String ruta, String nombreArchivo) {
        if (ruta == null || ruta.isBlank()) ruta = RUTA_DEFECTO;
        if (nombreArchivo == null || nombreArchivo.isBlank()) nombreArchivo = "trajes_espaciales.dat";

        File archivo = new File(ruta, nombreArchivo);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(trajes);
            return "Serializado en: " + archivo.getAbsolutePath();
        } catch (IOException ex) {
            return "Error al serializar: " + ex.getMessage();
        }
    }

    /**
     * Deserializa una lista de trajes espaciales desde un archivo.
     * 
     * Si la ruta está vacía, se utiliza la ruta por defecto (carpeta actual).
     * Si el nombre del archivo está vacío, se utiliza "trajes_espaciales.dat".
     * Valida que los objetos desserializados sean instancias de {@link Traje_Espacial}.
     * 
     * @param ruta la ruta del directorio donde está el archivo (puede ser null)
     * @param nombreArchivo el nombre del archivo a leer (puede ser null)
     * @return la lista de trajes cargada, o null si el archivo no existe o hay error
     * 
     * @see #RUTA_DEFECTO
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Traje_Espacial> deserializar(String ruta, String nombreArchivo) {
        if (ruta == null || ruta.isBlank()) ruta = RUTA_DEFECTO;
        if (nombreArchivo == null || nombreArchivo.isBlank()) nombreArchivo = "trajes_espaciales.dat";

        File archivo = new File(ruta, nombreArchivo);
        if (!archivo.exists()) return null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> raw = (List<?>) obj;
                List<Traje_Espacial> nueva = new ArrayList<>();
                for (Object o : raw) {
                    if (o instanceof Traje_Espacial) nueva.add((Traje_Espacial) o);
                }
                this.trajes = nueva;
                return nueva;
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al deserializar: " + ex.getMessage());
        }
        return null;
    }

    // ---------- Helpers: acceso por reflexión a campos protegidos ----------

    /**
     * Obtiene el identificador de un traje espacial utilizando reflexión.
     * 
     * Accede al campo protegido "id_traje" de la clase {@link Traje_Espacial}.
     * 
     * @param t el traje espacial del cual obtener el id
     * @return el identificador del traje, o null si no se puede acceder al campo
     * 
     * @see #leerCampo(Traje_Espacial, String)
     */
    public String obtenerId(Traje_Espacial t) {
        return (String) leerCampo(t, "id_traje");
    }

    /**
     * Obtiene la talla de un traje espacial utilizando reflexión.
     * 
     * Accede al campo protegido "talla" de la clase {@link Traje_Espacial}.
     * 
     * @param t el traje espacial del cual obtener la talla
     * @return la talla del traje, o null si no se puede acceder al campo
     * 
     * @see #leerCampo(Traje_Espacial, String)
     */
    public String leerTalla(Traje_Espacial t) {
        return (String) leerCampo(t, "talla");
    }

    /**
     * Obtiene el peso soportado de un traje espacial utilizando reflexión.
     * 
     * Accede al campo protegido "peso_soportado" de la clase {@link Traje_Espacial}.
     * Convierte automáticamente diferentes tipos numéricos (Float, Integer, etc.) a Double.
     * 
     * @param t el traje espacial del cual obtener el peso
     * @return el peso en kilogramos como Double, o null si no se puede acceder o convertir
     * 
     * @see #leerCampo(Traje_Espacial, String)
     */
    public Double leerPeso(Traje_Espacial t) {
        Object v = leerCampo(t, "peso_soportado");
        if (v instanceof Double) return (Double) v;
        if (v instanceof Float) return ((Float) v).doubleValue();
        if (v instanceof Number) return ((Number) v).doubleValue();
        return null;
    }

    /**
     * Obtiene la fecha de inspección de un traje espacial utilizando reflexión.
     * 
     * Accede al campo protegido "fecha_inspeccion" de la clase {@link Traje_Espacial}.
     * 
     * @param t el traje espacial del cual obtener la fecha
     * @return la fecha de inspección, o null si no se puede acceder al campo
     * 
     * @see #leerCampo(Traje_Espacial, String)
     */
    public String leerFecha(Traje_Espacial t) {
        return (String) leerCampo(t, "fecha_inspeccion");
    }

    /**
     * Lee un campo protegido de un objeto {@link Traje_Espacial} utilizando reflexión.
     * 
     * Este método accede a campos privados o protegidos de la clase {@link Traje_Espacial}
     * mediante el uso de reflexión, evitando excepciones si el campo no existe.
     * 
     * @param t el traje espacial del cual leer el campo
     * @param nombreCampo el nombre exacto del campo a leer
     * @return el valor del campo, o null si el traje es null o el campo no existe/no se puede acceder
     * 
     * @see java.lang.reflect.Field
     */
    private Object leerCampo(Traje_Espacial t, String nombreCampo) {
        if (t == null) return null;
        try {
            Field f = Traje_Espacial.class.getDeclaredField(nombreCampo);
            f.setAccessible(true);
            return f.get(t);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            // no podemos leer, devolvemos null
            return null;
        }
    }

    /**
     * Obtiene la lista interna de trajes espaciales.
     * 
     * Retorna la referencia directa a la lista, no una copia.
     * 
     * @return la lista de trajes espaciales
     * 
     * @see #listar()
     */
    public List<Traje_Espacial> getTrajes() {
        return trajes;
    }
}