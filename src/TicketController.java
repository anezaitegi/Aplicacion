import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import Clases.Cine;
import Clases.Cliente;
import Clases.DAO;
import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicketController implements Initializable {

    // Variable con el nombre del cine, un array con los cines, otro con las
    // sesiones compradas y por último una variable de la clase Cliente con los
    // datos de comprador
    private static Cliente cliente;
    private static Funcion[] compras;
    private static Cine[] listaCines;
    private static String cine;

    @FXML
    private Label Fecha;

    @FXML
    private Button guardar;

    @FXML
    private Label infoCine;

    @FXML
    private Label nombreCine;

    @FXML
    private Label infoCliente;

    @FXML
    private Label infoEntradas;

    @FXML
    private Button inicio;

    // Metodo que pone cada variable recibida en la de la vista y carga la lista de
    // los cines desde la BBDD
    public static void setObjetos(Cliente client, Funcion[] funcions, String cineName) throws SQLException {
        cliente = client;
        compras = funcions;
        cine = cineName;
        listaCines = DAO.cargarCine();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Creamos una nueva fecha en el momento de abrir la vista y escribimos la misma
        Date fecha = new Date();
        Fecha.setText(fecha.toString());
        // Creamos una variable para concatenar todas la sesiones, una en cada linea
        String sesiones = "";
        for (int i = 0; i < compras.length; i++) {
            if (compras[i] != null) {
                sesiones = sesiones + compras[i].toString() + "\n";
            }
        }
        // y la escribimos
        infoEntradas.setText(sesiones);
        // Ponemos el nombre del cine y buscamos entre las cargadas de la BBDD para
        // poner la dirección de la misma
        nombreCine.setText(cine);
        for (int i = 0; i < listaCines.length; i++) {
            if (listaCines[i].getNombre().equals(cine)) {
                infoCine.setText(listaCines[i].getDireccion());
            }
        }
        // Escribimos la información del cliente
        infoCliente.setText(cliente.toString());
    }

    // Mensaje de confirmación de compra
    @FXML
    private void mostrarAlertConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Ticket guardado");
        alert.setContentText("¡Ticket guardado correctamente!");
        alert.showAndWait();
    }

    // Metodo para guardar la información del ticket en un fichero TXT
    @FXML
    void guardarTXT(ActionEvent event) {
        // Guardamos la ruta en una variable
        String ruta = "C:\\Users\\Usuario\\Desktop\\Reto3\\Aplicacion\\src\\Tickets.txt";
        try {
            // Creamos un BufferedWriter para escribir y guardamos toda la información
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ruta, true));
            escribir.newLine();
            escribir.write(infoEntradas.getText());
            escribir.write(nombreCine.getText());
            escribir.newLine();
            escribir.write(infoCine.getText());
            escribir.newLine();
            escribir.write(infoCliente.getText());
            escribir.newLine();
            escribir.write(Fecha.getText());
            escribir.newLine();
            escribir.write("----------");
            escribir.close();
        } catch (FileNotFoundException fn) {
            System.out.println("No se encuentra el fichero");
        } catch (IOException io) {
            System.out.println("Error de E/S ");
        }
        // Ponemos la confirmaión de que se ha guardado y deshabilitamos el boton para
        // no guardar más de una vez
        mostrarAlertConfirmacion();
        guardar.setDisable(true);
    }

    // Metodo para volver al inicio
    @FXML
    void startAgain(ActionEvent event) throws IOException {
        Main.setRoot("Bienvenida");
    }

}