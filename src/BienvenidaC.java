
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BienvenidaC implements Initializable {

    // Reiniciar todas las variables
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Reiniciamos todas las variables
        
    }

    @FXML
    private AnchorPane pantallaSeguir;

    // Pasar al login
    @FXML
    void cambiarVista(MouseEvent event) throws IOException {
        Main.setRoot("Login");
    }
}