module co.edu.poli.Proyecto_Space_Suit_Manager {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.poli.Proyecto_Space_Suit_Manager to javafx.fxml;
    exports co.edu.poli.Proyecto_Space_Suit_Manager;

    opens Space_Suit_Manager.Controller to javafx.fxml;
    exports Space_Suit_Manager.Controller;

    opens Space_Suit_Manager.modelo to javafx.fxml;
    exports Space_Suit_Manager.modelo;

    opens Space_Suit_Manager.Servicios to javafx.fxml;
    exports Space_Suit_Manager.Servicios;
}
