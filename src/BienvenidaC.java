import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Clases.DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BienvenidaC implements Initializable {

    @FXML
    private AnchorPane pantallaSeguir;

    // Cargamos la conexión con la BBDD para que la aplicación sea más rápida
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        DAO.openConnection();
    }

    @FXML
    void cambiarVista(MouseEvent event) throws IOException {
        CinesController.setReinicio(true);
        Main.setRoot("Cines");
    }
}