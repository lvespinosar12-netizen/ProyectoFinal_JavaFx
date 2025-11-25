package Space_Suit_Manager.Controller;

import Space_Suit_Manager.Servicios.ImplementacionOperacionTraje;
import Space_Suit_Manager.Servicios.OperacionTraje;
import Space_Suit_Manager.modelo.Traje_Espacial;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Controlador principal de la interfaz gráfica para gestionar trajes espaciales.
 * 
 * Este controlador maneja todas las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * de trajes espaciales, así como la serialización y deserialización de datos.
 * 
 * Utiliza {@link ImplementacionOperacionTraje} para las operaciones de negocio,
 * que accede a los campos protegidos de {@link Traje_Espacial} mediante reflexión.
 * La clase interna {@link DisplayTraje} actúa como un adaptador para mostrar
 * los datos en la TableView de JavaFX.
 * 
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 * @see ImplementacionOperacionTraje
 * @see Traje_Espacial
 * @see DisplayTraje
 */
public class TrajeController {

    // ========== Controles FXML de entrada ==========
    
    /** Campo de texto para ingresar el ID del traje espacial. */
    @FXML private TextField txtId;
    
    /** Campo de texto para ingresar la talla del traje espacial. */
    @FXML private TextField txtTalla;
    
    /** Campo de texto para ingresar el peso soportado del traje en kilogramos. */
    @FXML private TextField txtPeso;
    
    /** Selector de fecha para la fecha de inspección del traje. */
    @FXML private DatePicker dateInspeccion;

    // ========== Controles FXML de tabla ==========
    
    /** Tabla que muestra la lista de trajes espaciales. */
    @FXML private TableView<DisplayTraje> tblTrajes;
    
    /** Columna que muestra los identificadores de los trajes. */
    @FXML private TableColumn<DisplayTraje, String> colId;
    
    /** Columna que muestra las tallas de los trajes. */
    @FXML private TableColumn<DisplayTraje, String> colTalla;
    
    /** Columna que muestra los pesos soportados de los trajes. */
    @FXML private TableColumn<DisplayTraje, Double> colPeso;
    
    /** Columna que muestra las fechas de inspección de los trajes. */
    @FXML private TableColumn<DisplayTraje, String> colFecha;

    // ========== Servicios y datos ==========
    
    /** Servicio que implementa las operaciones CRUD y serialización de trajes. */
    private ImplementacionOperacionTraje servicio = new ImplementacionOperacionTraje();

    /** Lista observable que alimenta la TableView con objetos DisplayTraje. */
    private ObservableList<DisplayTraje> listaDisplay = FXCollections.observableArrayList();

    /**
     * Inicializa el controlador y configura los elementos de la interfaz gráfica.
     * 
     * Este método se ejecuta automáticamente después de cargar el archivo FXML.
     * Configura las columnas de la tabla, carga datos previamente guardados
     * (si existen) desde el archivo de serialización.
     */
    @FXML
    public void initialize() {
        // configurar columnas
        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colTalla.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTalla()));
        colPeso.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getPeso()).asObject());
        colFecha.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFecha()));

        tblTrajes.setItems(listaDisplay);

        // si existe archivo, cargarlo silenciosamente
        List<Traje_Espacial> cargados = servicio.deserializar(".", "trajes_espaciales.dat");
        if (cargados != null) {
            rebuildDisplayFromModels(cargados);
        }
    }

    /**
     * Guarda un nuevo traje espacial en el sistema.
     * 
     * Valida que todos los campos sean ingresados correctamente antes de guardar.
     * Si el traje ya existe (por ID), muestra un mensaje de error.
     * 
     * @param e el evento de acción que desencadena este método
     * 
     * @see #limpiarCampos()
     * @see #mostrarAlerta(String, String)
     */
    @FXML
    private void guardarTraje(ActionEvent e) {
        String id = txtId.getText().trim();
        String talla = txtTalla.getText().trim();
        String pesoTxt = txtPeso.getText().trim();
        String fecha = (dateInspeccion.getValue() != null) ? dateInspeccion.getValue().toString() : "";

        if (id.isEmpty() || talla.isEmpty() || pesoTxt.isEmpty() || fecha.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(pesoTxt);
        } catch (NumberFormatException ex) {
            mostrarAlerta("Error", "Peso no válido.");
            return;
        }

        Traje_Espacial nuevo = new Traje_Espacial(id, talla, peso, fecha);

        boolean ok = servicio.guardar(nuevo);
        if (!ok) {
            mostrarAlerta("Error", "Ya existe un traje con ese ID.");
            return;
        }

        listaDisplay.add(DisplayTraje.fromModel(nuevo, servicio));
        limpiarCampos();
        mostrarAlerta("Éxito", "Traje guardado.");
    }

    /**
     * Modifica un traje espacial existente en el sistema.
     * 
     * Busca un traje por ID y lo reemplaza con los datos ingresados en los campos.
     * Si el traje no existe, muestra un mensaje de error.
     * 
     * @param e el evento de acción que desencadena este método
     * 
     * @see #findIndexById(String)
     * @see #mostrarAlerta(String, String)
     */
    @FXML
    private void modificarTraje(ActionEvent e) {
        String id = txtId.getText().trim();
        if (id.isEmpty()) { mostrarAlerta("Error", "Ingrese ID para modificar."); return; }

        String talla = txtTalla.getText().trim();
        String pesoTxt = txtPeso.getText().trim();
        String fecha = (dateInspeccion.getValue() != null) ? dateInspeccion.getValue().toString() : "";

        double peso;
        try { peso = Double.parseDouble(pesoTxt); }
        catch (NumberFormatException ex) { mostrarAlerta("Error", "Peso no válido."); return; }

        Traje_Espacial mod = new Traje_Espacial(id, talla, peso, fecha);
        boolean ok = servicio.modificar(id, mod);
        if (!ok) { mostrarAlerta("Error", "No existe traje con ese ID."); return; }

        // actualizar display
        int idx = findIndexById(id);
        if (idx >= 0) {
            listaDisplay.set(idx, DisplayTraje.fromModel(mod, servicio));
        }

        mostrarAlerta("Éxito", "Traje modificado.");
    }

    /**
     * Elimina un traje espacial del sistema.
     * 
     * Busca un traje por ID y lo elimina de la colección.
     * Si el traje no existe, muestra un mensaje de error.
     * 
     * @param e el evento de acción que desencadena este método
     * 
     * @see #findIndexById(String)
     * @see #limpiarCampos()
     */
    @FXML
    private void eliminarTraje(ActionEvent e) {
        String id = txtId.getText().trim();
        if (id.isEmpty()) { mostrarAlerta("Error", "Ingrese ID para eliminar."); return; }

        boolean ok = servicio.eliminar(id);
        if (!ok) { mostrarAlerta("Error", "No existe traje con ese ID."); return; }

        int idx = findIndexById(id);
        if (idx >= 0) listaDisplay.remove(idx);

        limpiarCampos();
        mostrarAlerta("Éxito", "Traje eliminado.");
    }

    /**
     * Busca un traje espacial por su identificador.
     * 
     * Si encuentra el traje, carga sus datos en los campos de entrada.
     * Utiliza reflexión a través del servicio para acceder a los datos del modelo.
     * 
     * @param e el evento de acción que desencadena este método
     * 
     * @see ImplementacionOperacionTraje#buscar(String)
     * @see ImplementacionOperacionTraje#leerTalla(Traje_Espacial)
     * @see ImplementacionOperacionTraje#leerPeso(Traje_Espacial)
     * @see ImplementacionOperacionTraje#leerFecha(Traje_Espacial)
     */
    @FXML
    private void buscarTraje(ActionEvent e) {
        String id = txtId.getText().trim();
        if (id.isEmpty()) { mostrarAlerta("Error", "Ingrese ID para buscar."); return; }

        Traje_Espacial t = servicio.buscar(id);
        if (t == null) { mostrarAlerta("Sin resultados", "No se encontró el traje."); return; }

        // llenar campos desde el modelo (usando servicio/reflexión)
        txtTalla.setText(servicio.leerTalla(t));
        Double p = servicio.leerPeso(t);
        txtPeso.setText(p == null ? "" : String.valueOf(p));
        txtId.setText(id);
        String f = servicio.leerFecha(t);
        try {
            if (f != null && !f.isBlank()) dateInspeccion.setValue(LocalDate.parse(f));
        } catch (DateTimeParseException ex) {
            // ignora formato inválido en fecha
        }

        mostrarAlerta("Resultado", "Traje encontrado.");
    }

    /**
     * Actualiza la tabla con la lista completa de trajes espaciales.
     * 
     * Obtiene todos los trajes del servicio y reconstruye la lista de display.
     * 
     * @param e el evento de acción que desencadena este método
     * 
     * @see #rebuildDisplayFromModels(List)
     */
    @FXML
    private void listarTrajes(ActionEvent e) {
        rebuildDisplayFromModels(servicio.listar());
        mostrarAlerta("Lista", "Tabla actualizada.");
    }

    /**
     * Serializa todos los trajes espaciales a un archivo.
     * 
     * Guarda la colección de trajes en formato binario en el archivo especificado.
     * 
     * @param e el evento de acción que desencadena este método
     * 
     * @see ImplementacionOperacionTraje#serializar(String, String)
     */
    @FXML
    private void serializarTrajes(ActionEvent e) {
        String msg = servicio.serializar(".", "trajes_espaciales.dat");
        mostrarAlerta("Serializar", msg);
    }

    /**
     * Deserializa los trajes espaciales desde un archivo.
     * 
     * Carga la colección de trajes previamente guardada desde el archivo especificado
     * y actualiza la tabla.
     * 
     * @param e el evento de acción que desencadena este método
     * 
     * @see ImplementacionOperacionTraje#deserializar(String, String)
     * @see #rebuildDisplayFromModels(List)
     */
    @FXML
    private void deserializarTrajes(ActionEvent e) {
        List<Traje_Espacial> cargados = servicio.deserializar(".", "trajes_espaciales.dat");
        if (cargados == null) { mostrarAlerta("Error", "No se pudo leer archivo."); return; }
        rebuildDisplayFromModels(cargados);
        mostrarAlerta("Deserializar", "Datos cargados desde archivo.");
    }

    /**
     * Cierra la aplicación.
     * 
     * @param e el evento de acción que desencadena este método
     */
    @FXML
    private void salirApp(ActionEvent e) {
        System.exit(0);
    }

    /**
     * Carga los datos del traje seleccionado en la tabla a los campos de entrada.
     * 
     * Permite al usuario editar un traje haciendo clic en la tabla.
     */
    @FXML
    private void mostrarSeleccion() {
        DisplayTraje sel = tblTrajes.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        txtId.setText(sel.getId());
        txtTalla.setText(sel.getTalla());
        txtPeso.setText(String.valueOf(sel.getPeso()));
        try {
            if (sel.getFecha() != null && !sel.getFecha().isBlank())
                dateInspeccion.setValue(LocalDate.parse(sel.getFecha()));
        } catch (DateTimeParseException ex) { /* ignore */ }
    }

    // ========== Métodos auxiliares ==========

    /**
     * Reconstruye la lista de display a partir de una lista de modelos de trajes.
     * 
     * Convierte objetos {@link Traje_Espacial} en objetos {@link DisplayTraje}
     * para mostrarlos en la tabla.
     * 
     * @param modelos la lista de trajes espaciales del modelo
     * 
     * @see DisplayTraje#fromModel(Traje_Espacial, ImplementacionOperacionTraje)
     */
    private void rebuildDisplayFromModels(List<Traje_Espacial> modelos) {
        listaDisplay.clear();
        if (modelos == null) return;
        for (Traje_Espacial t : modelos) {
            listaDisplay.add(DisplayTraje.fromModel(t, servicio));
        }
    }

    /**
     * Busca el índice de un traje en la lista de display por su identificador.
     * 
     * La búsqueda es insensible a mayúsculas y minúsculas.
     * 
     * @param id el identificador del traje a buscar
     * @return el índice del traje en la lista, o -1 si no se encuentra
     */
    private int findIndexById(String id) {
        for (int i = 0; i < listaDisplay.size(); i++) {
            if (listaDisplay.get(i).getId().equalsIgnoreCase(id)) return i;
        }
        return -1;
    }

    /**
     * Limpia todos los campos de entrada.
     * 
     * Restaura los campos a su estado inicial vacío.
     */
    private void limpiarCampos() {
        txtId.clear();
        txtTalla.clear();
        txtPeso.clear();
        dateInspeccion.setValue(null);
    }

    /**
     * Muestra un cuadro de alerta con un título y mensaje específico.
     * 
     * @param titulo el título de la alerta
     * @param mensaje el contenido del mensaje de la alerta
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }

    // ========== Clase interna: DisplayTraje (Adaptador para TableView) ==========

    /**
     * Clase interna que actúa como adaptador entre el modelo {@link Traje_Espacial}
     * y la presentación en la TableView de JavaFX.
     * 
     * Proporciona getters accesibles para que JavaFX pueda mostrar los datos
     * en la tabla, ya que {@link Traje_Espacial} no expone sus campos protegidos.
     * 
     * @author [Tu nombre]
     * @version 1.0
     * @since 2025
     * @see Traje_Espacial
     */
    public static class DisplayTraje {
        
        /** Identificador del traje espacial. */
        private final String id;
        
        /** Talla del traje espacial. */
        private final String talla;
        
        /** Peso soportado del traje espacial en kilogramos. */
        private final double peso;
        
        /** Fecha de inspección del traje espacial. */
        private final String fecha;

        /**
         * Constructor de DisplayTraje.
         * 
         * @param id el identificador del traje
         * @param talla la talla del traje
         * @param peso el peso soportado del traje
         * @param fecha la fecha de inspección del traje
         */
        public DisplayTraje(String id, String talla, double peso, String fecha) {
            this.id = id;
            this.talla = talla;
            this.peso = peso;
            this.fecha = fecha;
        }

        /**
         * Obtiene el identificador del traje.
         * 
         * @return el id del traje
         */
        public String getId() { return id; }
        
        /**
         * Obtiene la talla del traje.
         * 
         * @return la talla del traje
         */
        public String getTalla() { return talla; }
        
        /**
         * Obtiene el peso soportado del traje.
         * 
         * @return el peso en kilogramos
         */
        public double getPeso() { return peso; }
        
        /**
         * Obtiene la fecha de inspección del traje.
         * 
         * @return la fecha de inspección
         */
        public String getFecha() { return fecha; }

        /**
         * Convierte un objeto {@link Traje_Espacial} (modelo) a un {@link DisplayTraje}.
         * 
         * Utiliza {@link ImplementacionOperacionTraje} con reflexión para acceder
         * a los campos protegidos del modelo.
         * 
         * @param t el traje espacial del modelo
         * @param servicio el servicio que proporciona acceso a los campos mediante reflexión
         * @return un nuevo DisplayTraje con los datos del modelo
         * 
         * @see ImplementacionOperacionTraje#obtenerId(Traje_Espacial)
         * @see ImplementacionOperacionTraje#leerTalla(Traje_Espacial)
         * @see ImplementacionOperacionTraje#leerPeso(Traje_Espacial)
         * @see ImplementacionOperacionTraje#leerFecha(Traje_Espacial)
         */
        public static DisplayTraje fromModel(Traje_Espacial t, ImplementacionOperacionTraje servicio) {
            String id = servicio.obtenerId(t);
            String talla = servicio.leerTalla(t);
            Double pesoD = servicio.leerPeso(t);
            String fecha = servicio.leerFecha(t);
            double peso = (pesoD == null) ? 0.0 : pesoD;
            return new DisplayTraje(id == null ? "" : id,
                    talla == null ? "" : talla,
                    peso,
                    fecha == null ? "" : fecha);
        }
    }
}