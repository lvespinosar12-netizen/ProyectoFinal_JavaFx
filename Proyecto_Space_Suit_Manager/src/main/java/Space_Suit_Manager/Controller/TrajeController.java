package Space_Suit_Manager.Controller;

import PanelDeControl.servicios.ImplementacionOperacionTraje;
import PanelDeControl.servicios.OperacionTraje;
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
 * Controller que trabaja con Traje_Espacial (sin getters) usando ImplementacionOperacionTraje (que usa reflexión).
 * La TableView usa la clase interna DisplayTraje (con getters) para mostrar los valores.
 */
public class TrajeController {

    @FXML private TextField txtId;
    @FXML private TextField txtTalla;
    @FXML private TextField txtPeso;
    @FXML private DatePicker dateInspeccion;

    @FXML private TableView<DisplayTraje> tblTrajes;
    @FXML private TableColumn<DisplayTraje, String> colId;
    @FXML private TableColumn<DisplayTraje, String> colTalla;
    @FXML private TableColumn<DisplayTraje, Double> colPeso;
    @FXML private TableColumn<DisplayTraje, String> colFecha;

    // servicio (CRUD + serialización)
    private ImplementacionOperacionTraje servicio = new ImplementacionOperacionTraje();

    // lista para la tabla (DisplayTraje)
    private ObservableList<DisplayTraje> listaDisplay = FXCollections.observableArrayList();

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

    @FXML
    private void listarTrajes(ActionEvent e) {
        rebuildDisplayFromModels(servicio.listar());
        mostrarAlerta("Lista", "Tabla actualizada.");
    }

    @FXML
    private void serializarTrajes(ActionEvent e) {
        String msg = servicio.serializar(".", "trajes_espaciales.dat");
        mostrarAlerta("Serializar", msg);
    }

    @FXML
    private void deserializarTrajes(ActionEvent e) {
        List<Traje_Espacial> cargados = servicio.deserializar(".", "trajes_espaciales.dat");
        if (cargados == null) { mostrarAlerta("Error", "No se pudo leer archivo."); return; }
        rebuildDisplayFromModels(cargados);
        mostrarAlerta("Deserializar", "Datos cargados desde archivo.");
    }

    @FXML
    private void salirApp(ActionEvent e) {
        // cerrar la aplicación
        System.exit(0);
    }

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

    // ------------- helpers ---------------

    private void rebuildDisplayFromModels(List<Traje_Espacial> modelos) {
        listaDisplay.clear();
        if (modelos == null) return;
        for (Traje_Espacial t : modelos) {
            listaDisplay.add(DisplayTraje.fromModel(t, servicio));
        }
    }

    private int findIndexById(String id) {
        for (int i = 0; i < listaDisplay.size(); i++) {
            if (listaDisplay.get(i).getId().equalsIgnoreCase(id)) return i;
        }
        return -1;
    }

    private void limpiarCampos() {
        txtId.clear();
        txtTalla.clear();
        txtPeso.clear();
        dateInspeccion.setValue(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(mensaje);
        a.showAndWait();
    }

    // ---------------- DisplayTraje (wrapper para TableView) ----------------

    public static class DisplayTraje {
        private final String id;
        private final String talla;
        private final double peso;
        private final String fecha;

        public DisplayTraje(String id, String talla, double peso, String fecha) {
            this.id = id;
            this.talla = talla;
            this.peso = peso;
            this.fecha = fecha;
        }

        public String getId() { return id; }
        public String getTalla() { return talla; }
        public double getPeso() { return peso; }
        public String getFecha() { return fecha; }

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
