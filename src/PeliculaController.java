import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PeliculaController implements Initializable {

    private String cine;

    @FXML
    private Button atras;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private ImageView img7;

    @FXML
    private ImageView img8;

    @FXML
    private Label nombreCine;

    public void setCine(String cine) {
        this.cine = cine;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        nombreCine.setText(cine);
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Cines");
    }

}
