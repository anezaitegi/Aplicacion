import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField Dni;

    @FXML
    private Button atras;

    @FXML
    private Button continuar;

    @FXML
    private TextField pass;

    @FXML
    void verficar(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Registrate");
    }

}
