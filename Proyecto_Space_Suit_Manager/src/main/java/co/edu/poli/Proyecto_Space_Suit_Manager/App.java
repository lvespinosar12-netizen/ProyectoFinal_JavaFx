package co.edu.poli.Proyecto_Space_Suit_Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación de Gestión de Trajes Espaciales.
 *
 * Esta clase extiende {@link javafx.application.Application} e implementa
 * la interfaz gráfica de usuario (GUI) utilizando JavaFX y archivos FXML.
 * La aplicación permite gestionar información sobre trajes espaciales.
 *
 * @author Laura Espinosa y Jhosayde Leon
 * @version 2.0
 * @since 2025
 */
public class App extends Application {

    /** La escena principal de la aplicación. */
    private static Scene scene;

    /**
     * Inicia la aplicación y configura la ventana principal.
     *
     * Este método se ejecuta cuando se inicia la aplicación. Carga el archivo FXML
     * del formulario, crea la escena con las dimensiones especificadas (900x600),
     * establece el título de la ventana y muestra la interfaz gráfica.
     *
     * @param stage la ventana principal de la aplicación proporcionada por JavaFX
     * @throws Exception si ocurre un error al cargar el archivo FXML
     *
     * @see #loadFXML(String)
     */
    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("Formulario"), 900, 600);
        stage.setTitle("Gestión de Trajes Espaciales");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga un archivo FXML y retorna su raíz como un nodo {@link Parent}.
     *
     * Este método utiliza {@link javafx.fxml.FXMLLoader} para cargar archivos FXML
     * desde los recursos de la aplicación. El archivo debe estar ubicado en la carpeta
     * resources/co/edu/poli/Proyecto_Space_Suit_Manager/ con la extensión ".fxml".
     *
     * @param resource el nombre del archivo FXML sin la extensión
     *                 (ej: "Formulario")
     * @return el nodo raíz del archivo FXML cargado
     * @throws Exception si ocurre un error durante la carga del archivo FXML,
     *                   por ejemplo si el archivo no existe o está mal formado
     *
     * @see javafx.fxml.FXMLLoader
     */
    private static Parent loadFXML(String resource) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(resource + ".fxml"));
        return loader.load();
    }

    /**
     * Punto de entrada principal de la aplicación.
     *
     * Este método invoca el método {@link #launch(String[])} que inicia
     * la aplicación JavaFX y ejecuta el método {@link #start(Stage)}.
     *
     * @param args argumentos de línea de comandos (no utilizados en esta aplicación)
     */
    public static void main(String[] args) {
        launch();
    }
}