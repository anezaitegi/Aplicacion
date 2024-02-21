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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PeliculasController implements Initializable {

    // Variable con el nombre del cine que se ha elegido y un array de la clase
    // Peliculas con las pelis disponibles en ese mismo cine
    private static String cineNombre;
    private static Pelicula[] listaPeliculas;
    // Array con las etiquetas del titulo y la imagen que contendran las pelis
    private Label[] titulos;
    private ImageView[] imgs;

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
    private Label nombreCine;

    @FXML
    private Label peli1;

    @FXML
    private Label peli2;

    @FXML
    private Label peli3;

    @FXML
    private Label peli4;

    // Metodo que recibe el cine y lo guarda en la variable de arriba y carga las
    // peliculas desde la BBDD
    public static void setCineNombre(String cine) throws Exception {
        cineNombre = cine;
        listaPeliculas = DAO.cargarPeliculas(cine);
    }

    // Pone el nombre del cine en la etiqueta del titulo, guarda los Label e
    // ImageView en sus respectivos arrays y llama al metodo para poner las pelis
    // antes de cargar la vista
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        nombreCine.setText(cineNombre);
        Label[] t = { peli1, peli2, peli3, peli4 };
        this.titulos = t;
        ImageView[] i = { img1, img2, img3, img4 };
        this.imgs = i;
        ponerPelis();
    }

    // Metodo que pone una peli en cada Label e ImageView y desabilita las que no se
    // han utilizado
    @FXML
    public void ponerPelis() {
        for (int i = 0; i < 4; i++) {
            if (listaPeliculas[i] != null) {
                titulos[i].setText(listaPeliculas[i].getTitulo());
                Image portada = new Image(listaPeliculas[i].getImgURL());
                imgs[i].setImage(portada);
            } else {
                titulos[i].setDisable(true);
                imgs[i].setDisable(true);
            }
        }

    }

    // Metodos que llevan a la vista de Sesiones y mandan a su vez la pelicula
    // seleccionada y el nombre del cine
    @FXML
    void elegir1(MouseEvent event) throws Exception {
        SesionesController.setPelicula(listaPeliculas[0], cineNombre);
        Main.setRoot("Sesiones");
    }

    @FXML
    void elegir2(MouseEvent event) throws Exception {
        SesionesController.setPelicula(listaPeliculas[1], cineNombre);
        Main.setRoot("Sesiones");
    }

    @FXML
    void elegir3(MouseEvent event) throws Exception {
        SesionesController.setPelicula(listaPeliculas[2], cineNombre);
        Main.setRoot("Sesiones");
    }

    @FXML
    void elegir4(MouseEvent event) throws Exception {
        SesionesController.setPelicula(listaPeliculas[3], cineNombre);
        Main.setRoot("Sesiones");
    }

    // Metodo para volver a la vista Cines
    @FXML
    void volver(ActionEvent event) throws IOException {
        CinesController.setCine(true);
        Main.setRoot("Cines");
    }

}