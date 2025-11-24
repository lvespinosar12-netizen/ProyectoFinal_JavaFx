package PanelDeControl.servicios;

import Space_Suit_Manager.modelo.Traje_Espacial;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImplementacionOperacionTraje implements OperacionTraje {

    private List<Traje_Espacial> trajes;
    private final String RUTA_DEFECTO = "."; // carpeta actual por defecto

    public ImplementacionOperacionTraje() {
        this.trajes = new ArrayList<>();
    }

    // ---------- CRUD ----------

    @Override
    public boolean guardar(Traje_Espacial traje) {
        String id = obtenerId(traje);
        if (id == null) return false;
        if (buscar(id) != null) return false; // ya existe
        return trajes.add(traje);
    }

    @Override
    public List<Traje_Espacial> listar() {
        return new ArrayList<>(trajes);
    }

    @Override
    public Traje_Espacial buscar(String id) {
        if (id == null) return null;
        for (Traje_Espacial t : trajes) {
            String tid = obtenerId(t);
            if (tid != null && tid.equalsIgnoreCase(id)) return t;
        }
        return null;
    }

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

    @Override
    public boolean eliminar(String id) {
        if (id == null) return false;
        return trajes.removeIf(t -> {
            String tid = obtenerId(t);
            return (tid != null && tid.equalsIgnoreCase(id));
        });
    }

    // ---------- SERIALIZACIÓN ----------

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

    public String obtenerId(Traje_Espacial t) {
        return (String) leerCampo(t, "id_traje");
    }

    public String leerTalla(Traje_Espacial t) {
        return (String) leerCampo(t, "talla");
    }

    public Double leerPeso(Traje_Espacial t) {
        Object v = leerCampo(t, "peso_soportado");
        if (v instanceof Double) return (Double) v;
        if (v instanceof Float) return ((Float) v).doubleValue();
        if (v instanceof Number) return ((Number) v).doubleValue();
        return null;
    }

    public String leerFecha(Traje_Espacial t) {
        return (String) leerCampo(t, "fecha_inspeccion");
    }

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

    // getter para controller si necesita la lista directa
    public List<Traje_Espacial> getTrajes() {
        return trajes;
    }
}
