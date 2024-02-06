import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Clases.DAO;
import Clases.Funcion;
import Clases.Pelicula;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Seleccion1Controller implements Initializable {

    private static Pelicula peli;
    private static Funcion[] funciones;

    @FXML
    private Button atras;

    @FXML
    private DatePicker calendario;

    @FXML
    private Label elegir2;

    @FXML
    private Label elegir3;

    @FXML
    private Label funcion1;

    @FXML
    private ImageView img;

    public static void setPelicula(Pelicula pelicula, String cine) throws Exception {
        peli = pelicula;
        funciones = DAO.cargarFunciones(cine, pelicula);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    void atras(ActionEvent event) throws IOException {
        Main.setRoot("Pelicula");
    }

    @FXML
    void elegir1(MouseEvent event) {

    }

    @FXML
    void elegir2(MouseEvent event) {

    }

    @FXML
    void elegir3(MouseEvent event) {

    }

    @FXML
    void elegirFecha(ActionEvent event) {
        
    }

}
