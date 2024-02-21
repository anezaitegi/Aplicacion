import java.net.URL;
import java.util.ResourceBundle;
import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CinesController implements Initializable {

    // Tenemos un array de la clase Funciones con las sesiones elegidas para
    // pasarlas al carrito más tarde y una variable con el nombre del cine del que
    // se haya elegido la primera sesión para no poder elegir otro cine más tarde
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

    // Este metodo hace que si se ha elegido una sesión no se puedan elegir los
    // otros cines del que no sea la primera sesión antes de cargar la vista
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

    // Metodo para anular el cine si se ha dado al boton de atras la primera vez
    public static void setCine(boolean atras) {
        if (atras && funcionesElegidas[0] == null) {
            cine = null;
        }
    }

    // Metodo para reiniciar todo si se anula la compra o se vuelve al principio
    public static void setReinicio(boolean reiniciar) {
        if (reiniciar) {
            funcionesElegidas = new Funcion[100];
            cine = null;
        }
    }

    // Metodo para insertar la sesión elegida en el array
    public static void setFunciones(Funcion funcion) {
        for (int i = 0; i < funcionesElegidas.length; i++) {
            if (funcionesElegidas[i] == null) {
                funcionesElegidas[i] = funcion;
                break;
            }
        }
    }

    // Los siguientes tres metodos mandan la información del cine elegido a la vista
    // de Peliculas y pasa a esa misma vista
    @FXML
    void infoDonos(ActionEvent event) throws Exception {
        cine = "Donostia";
        PeliculasController.setCineNombre(cine);
        Main.setRoot("Peliculas");
    }

    @FXML
    void infoLasarte(ActionEvent event) throws Exception {
        cine = "Lasarte";
        PeliculasController.setCineNombre(cine);
        Main.setRoot("Peliculas");
    }

    @FXML
    void infoUsurbil(ActionEvent event) throws Exception {
        cine = "Usurbil";
        PeliculasController.setCineNombre(cine);
        Main.setRoot("Peliculas");
    }

    // Metodo que te lleva a resgistrarte si has elegido alguna sesión o que te
    // lleva al inicio si no has elegido ninguna. Además pasa el array de las
    // funciones a la siguiente vista
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