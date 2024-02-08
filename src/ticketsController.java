import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ticketsController implements Initializable {

    @FXML
    private Button guardar;

    @FXML
    private Label informacionCompra;

    @FXML
    private Button inicio;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void guardarTXT(ActionEvent event) {

    }

    @FXML
    void startAgain(ActionEvent event) throws IOException {
        Main.setRoot("Bienvenida");
    }

}
