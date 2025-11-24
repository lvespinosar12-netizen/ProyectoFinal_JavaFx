package PanelDeControl.servicios;

import Space_Suit_Manager.modelo.Traje_Espacial;
import java.util.List;

public interface OperacionTraje {

    boolean guardar(Traje_Espacial traje);

    List<Traje_Espacial> listar();

    Traje_Espacial buscar(String id);

    boolean modificar(String id, Traje_Espacial nuevoTraje);

    boolean eliminar(String id);

    String serializar(String ruta, String nombreArchivo);

    List<Traje_Espacial> deserializar(String ruta, String nombreArchivo);

}

