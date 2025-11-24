package co.edu.poli.Proyecto_Space_Suit_Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("/co/edu/poli/Proyecto_Space_Suit_Manager/Formulario"), 900, 600);
        stage.setTitle("Gestión de Trajes Espaciales");
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String resource) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(resource + ".fxml"));
        return loader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
