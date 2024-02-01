import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CinesController {

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
        PeliculaController.setCineNombre("Donostia");
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoLasarte(ActionEvent event) throws IOException {
        PeliculaController.setCineNombre("Lasarte");
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoUsurbil(ActionEvent event) throws IOException {
        PeliculaController.setCineNombre("Usurbil");
        Main.setRoot("Pelicula");
    }

}
