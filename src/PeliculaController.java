
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Clases.DAO;
import Clases.Pelicula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PeliculaController implements Initializable {

    private static String cineNombre;
    private static Pelicula[] listaPeliculas;

    @FXML
    private Button atras;

    @FXML
    private Button boton1;

    @FXML
    private Button boton2;

    @FXML
    private Button boton3;

    @FXML
    private Button boton4;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private Label nombreCine;

    @FXML
    private Label peli1;

    @FXML
    private Label peli2;

    @FXML
    private Label peli3;

    @FXML
    private Label peli4;

    @FXML
    void elegir1(ActionEvent event) throws Exception {
        Seleccion1Controller.setPelicula(listaPeliculas[0], cineNombre);
        Main.setRoot("Seleccion1");
    }

    @FXML
    void elegir2(ActionEvent event) throws Exception {
        Seleccion1Controller.setPelicula(listaPeliculas[1], cineNombre);
        Main.setRoot("Seleccion1");
    }

    @FXML
    void elegir3(ActionEvent event) throws Exception {
        Seleccion1Controller.setPelicula(listaPeliculas[2], cineNombre);
        Main.setRoot("Seleccion1");
    }

    @FXML
    void elegir4(ActionEvent event) throws Exception {
        Seleccion1Controller.setPelicula(listaPeliculas[3], cineNombre);
        Main.setRoot("Seleccion1");
    }

    private Button[] botones;
    private Label[] titulos;
    private ImageView[] imgs;

    public static void setCineNombre(String cine) throws Exception {
        cineNombre = cine;
        listaPeliculas = DAO.cargarPeliculas(cine);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        nombreCine.setText(cineNombre);
        Button[] b = {boton1, boton2, boton3, boton4};
        this.botones = b;
        Label[] t = { peli1, peli2, peli3, peli4 };
        this.titulos = t;
        ImageView[] i = { img1, img2, img3, img4 };
        this.imgs = i;
        ponerPelis();
    }

    @FXML
    public void ponerPelis() {
        for (int i = 0; i < 4; i++) {
            if (listaPeliculas[i] != null) {
                titulos[i].setText(listaPeliculas[i].getTitulo());
            } else {
                titulos[i].setDisable(true);
                imgs[i].setDisable(true);
                botones[i].setDisable(true);
            }
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Cines");
    }

}
