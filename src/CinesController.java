import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CinesController {

    private static Funcion[] funcionesElegidas = new Funcion[100];

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

    public static void setReinicio(boolean reiniciar) {
        if (reiniciar) {
            funcionesElegidas = new Funcion[100];
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
        PeliculaController.setCineNombre("Donostia");
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoLasarte(ActionEvent event) throws Exception {
        PeliculaController.setCineNombre("Lasarte");
        Main.setRoot("Pelicula");
    }

    @FXML
    void infoUsurbil(ActionEvent event) throws Exception {
        PeliculaController.setCineNombre("Usurbil");
        Main.setRoot("Pelicula");
    }

    @FXML
    void irCarrito(ActionEvent event) throws Exception {
        if (funcionesElegidas[0] != null) {
            RegistrateController.setFuncion(funcionesElegidas);
            Main.setRoot("Registrate");
        } else {
            Main.setRoot("Bienvenida");
        }

    }

}
