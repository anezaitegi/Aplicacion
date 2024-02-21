import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Clases.DAO;
import Clases.Funcion;
import Clases.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SesionesController implements Initializable {

    // Variable con el nombre de la peli y un array con las sesiones de la misma
    private static Pelicula peli;
    private static Funcion[] funciones;

    @FXML
    private TableColumn<Funcion, String> sala;

    @FXML
    private Button atras;

    @FXML
    private TableColumn<Funcion, String> fecha;

    @FXML
    private TableColumn<Funcion, String> hora;

    @FXML
    private ImageView img;

    @FXML
    private Button sumar;

    @FXML
    private TableView<Funcion> tablaFunciones;

    @FXML
    public ObservableList<Funcion> data;

    @FXML
    private Label titulo;

    @FXML
    private Label sinopsis;

    // Mensaje de alerta por no elegir una sesion
    @FXML
    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Sesion no elegida");
        alert.setContentText("¡Error! Por favor elija una sesión.");
        alert.showAndWait();
    }

    // Funcion para pasar la pelicula elegida y cargar las sesiones desde la BBDD
    public static void setPelicula(Pelicula pelicula, String cine) throws Exception {
        peli = pelicula;
        funciones = DAO.cargarFunciones(cine, pelicula);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Ponemos la imagen de la peli, el titulo y la descripción
        Image portada = new Image(peli.getImgURL());
        img.setImage(portada);
        titulo.setText(peli.getTitulo());
        sinopsis.setText(peli.getDescripcion());
        // además de cada sesion disponible
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        sala.setCellValueFactory(new PropertyValueFactory<>("sala"));
        data = FXCollections.observableArrayList();
        for (int i = 0; i < funciones.length; i++) {
            if (funciones[i] != null) {
                data.add(funciones[i]);
            }
        }
        tablaFunciones.setItems(data);
    }

    // Se lee si se ha elegido una Funcion de la tabla, se guarda en una clase
    // Funcion y se manda a la vista de Cines antes de ir a la misma
    @FXML
    void sumarFuncion(ActionEvent event) throws IOException {
        Funcion seleccion = tablaFunciones.getSelectionModel().getSelectedItem();
        if (tablaFunciones.getSelectionModel().getSelectedItem() == null) {
            mostrarAlertError();
        } else {
            CinesController.setFunciones(seleccion);
            Main.setRoot("Cines");
        }
    }

    // Metodo para volver sin elegir ninguna sesión a la vista de pelis
    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Peliculas");
    }

}