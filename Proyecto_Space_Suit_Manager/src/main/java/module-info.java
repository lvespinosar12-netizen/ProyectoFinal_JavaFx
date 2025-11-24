module co.edu.poli.Proyecto_Space_Suit_Manager {
    requires javafx.controls;
    requires javafx.fxml;

    // Para cargar FXML del paquete principal
    opens co.edu.poli.Proyecto_Space_Suit_Manager to javafx.fxml;

    // Para que JavaFX acceda a tus controladores
    opens Space_Suit_Manager.Controller to javafx.fxml;

    // Exportaciones (solo si otras partes del proyecto las necesitan)
    exports co.edu.poli.Proyecto_Space_Suit_Manager;
    exports Space_Suit_Manager.Controller;
    exports Space_Suit_Manager.modelo;
    exports PanelDeControl.servicios;
}

