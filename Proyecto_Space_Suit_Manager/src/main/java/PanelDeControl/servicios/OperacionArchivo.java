package PanelDeControl.servicios;

import Space_Suit_Manager.modelo.Traje_Espacial;
import java.util.List;

public interface OperacionArchivo {

    // CRUD obligatorio
    boolean guardar(Traje_Espacial traje);
    List<Traje_Espacial> listar();
    Traje_Espacial buscar(String codigo);
    boolean modificar(String codigo, Traje_Espacial nuevoTraje);
    boolean eliminar(String codigo);

    // Serialización
    String serializar(String ruta, String nombreArchivo);
    Traje_Espacial[] deserializar(String ruta, String nombreArchivo);

}
