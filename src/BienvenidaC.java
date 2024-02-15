
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class BienvenidaC {

    @FXML
    private AnchorPane pantallaSeguir;

    @FXML
    void cambiarVista(MouseEvent event) throws IOException {
        CinesController.setReinicio(true);
        Main.setRoot("Cines");
    }
}