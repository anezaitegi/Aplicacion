import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CinesController {

    private PeliculaController peliC = new PeliculaController();

    @FXML
    private Button cineDonosti;

    @FXML
    private Button cineLasarte;

    @FXML
    private Button cineUsurbil;

    @FXML
    private ImageView imgDonosti;

    @FXML
    private ImageView imgLasarte;

    @FXML
    private ImageView imgUsurbil;

    @FXML
    void infoDonos(ActionEvent event) throws IOException {
        peliC.setCine("Donostia");
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoLasarte(ActionEvent event) throws IOException {
        peliC.setCine("Lasarte");
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoUsurbil(ActionEvent event) throws IOException {
        peliC.setCine("Usurbil");
        Main.setRoot("Pelicula");
    }

}
