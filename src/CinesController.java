import java.net.URL;
import java.util.ResourceBundle;

import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CinesController implements Initializable {

    private static Funcion[] funcionesElegidas = new Funcion[100];
    private static String cine;

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
    private Button terminar;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        if (cine != null) {
            if (cine.equals("Donostia")) {
                cineLasarte.setDisable(true);
                cineUsurbil.setDisable(true);
                imgLasarte.setDisable(true);
                imgUsurbil.setDisable(true);
            }
            if (cine.equals("Lasarte")) {
                cineUsurbil.setDisable(true);
                cineDonosti.setDisable(true);
                imgUsurbil.setDisable(true);
                imgDonosti.setDisable(true);
            }
            if (cine.equals("Usurbil")) {
                cineDonosti.setDisable(true);
                cineLasarte.setDisable(true);
                imgDonosti.setDisable(true);
                imgLasarte.setDisable(true);
            }
        }
    }

    public static void setCine(boolean atras) {
        if (atras && funcionesElegidas[0] == null) {
            cine = null;
        }
    }

    public static void setReinicio(boolean reiniciar) {
        if (reiniciar) {
            funcionesElegidas = new Funcion[100];
            cine = null;
        }
    }

    public static void setFunciones(Funcion funcion) {
        for (int i=0; i<funcionesElegidas.length; i++) {
            if (funcionesElegidas[i] == null) {
                funcionesElegidas[i] = funcion;
                break;
            }
        }
    }

    @FXML
    void infoDonos(ActionEvent event) throws Exception {
        cine = "Donostia";
        PeliculaController.setCineNombre(cine);
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoLasarte(ActionEvent event) throws Exception {
        cine = "Lasarte";
        PeliculaController.setCineNombre(cine);
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoUsurbil(ActionEvent event) throws Exception {
        cine = "Usurbil";
        PeliculaController.setCineNombre(cine);
        Main.setRoot("Pelicula");
    }

    @FXML
    void irCarrito(ActionEvent event) throws Exception {
        if (funcionesElegidas[0] != null) {
            RegistrateController.setFuncion(funcionesElegidas, cine);
            Main.setRoot("Registrate");
        } else {
            Main.setRoot("Bienvenida");
        }

    }

}
