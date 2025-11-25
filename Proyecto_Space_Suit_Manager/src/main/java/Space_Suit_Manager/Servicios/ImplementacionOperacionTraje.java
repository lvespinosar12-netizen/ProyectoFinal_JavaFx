package Space_Suit_Manager.Servicios;

import Space_Suit_Manager.modelo.Traje_Espacial;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionOperacionTraje implements OperacionArchivo {

    private List<Traje_Espacial> trajes = new ArrayList<>();

    // ===========================================================
    //  MÉTODOS CRUD CORRECTOS
    // ===========================================================

    @Override
    public boolean guardar(Traje_Espacial t) {
        if (t == null) return false;

        String id = obtenerId(t);
        if (id == null || id.isBlank()) return false;

        // evitar duplicados
        if (buscar(id) != null) return false;

        trajes.add(t);
        return true;
    }

    @Override
    public boolean modificar(String id, Traje_Espacial nuevoTraje) {
        if (id == null || nuevoTraje == null) return false;

        Traje_Espacial encontrado = buscar(id);
        if (encontrado == null) return false;

        trajes.remove(encontrado);
        trajes.add(nuevoTraje);

        return true;
    }

    @Override
    public boolean eliminar(String id) {
        Traje_Espacial encontrado = buscar(id);
        if (encontrado == null) return false;

        trajes.remove(encontrado);
        return true;
    }

    @Override
    public Traje_Espacial buscar(String id) {
        if (id == null) return null;

        for (Traje_Espacial t : trajes) {
            String actual = obtenerId(t);
            if (id.equalsIgnoreCase(actual)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Traje_Espacial> listar() {
        return new ArrayList<>(trajes); // COPIA SEGURA
    }

    // ===========================================================
    //  SERIALIZAR / DESERIALIZAR (FUNCIONANDO)
    // ===========================================================

    @Override
    public String serializar(String ruta, String nombreArchivo) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(ruta + "/" + nombreArchivo))) {

            oos.writeObject(trajes);
            return "Datos guardados correctamente.";
        } catch (Exception e) {
            return "Error al serializar: " + e.getMessage();
        }
    }

    @Override
    public List<Traje_Espacial> deserializar(String ruta, String nombreArchivo) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(ruta + "/" + nombreArchivo))) {

            trajes = (List<Traje_Espacial>) ois.readObject();
            return trajes;
        } catch (Exception e) {
            System.out.println("Error al deserializar: " + e.getMessage());
            return null;
        }
    }

    // ===========================================================
    //  REFLEXIÓN (NOMBRE DE CAMPOS CORRECTOS)
    // ===========================================================

    private Object leerCampo(Traje_Espacial t, String nombre) {
        try {
            Field f = t.getClass().getDeclaredField(nombre);
            f.setAccessible(true);
            return f.get(t);
        } catch (Exception e) {
            return null;
        }
    }

    public String obtenerId(Traje_Espacial t) {
        return (String) leerCampo(t, "idTraje");
    }

    public String leerTalla(Traje_Espacial t) {
        return (String) leerCampo(t, "talla");
    }

    public Double leerPeso(Traje_Espacial t) {
        Object v = leerCampo(t, "pesoSoportado");
        return (v instanceof Number) ? ((Number) v).doubleValue() : null;
    }

    public String leerFecha(Traje_Espacial t) {
        return (String) leerCampo(t, "fechaInspeccion");
    }
}
