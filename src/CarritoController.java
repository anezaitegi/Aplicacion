import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import Clases.Cliente;
import Clases.DAO;
import Clases.Entrada;
import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CarritoController implements Initializable {

    // Variable del nombre del cine, de la clase cliente, y dos arrays una con las
    // sesiones de compra y otra con las entradas. También tenemos una variable de
    // contador para controlar la cantidad de sesiones
    private static Cliente cliente;
    private static Funcion[] compras;
    private static Entrada[] entradas;
    private static String cine;
    private int contador;

    @FXML
    private Button volver;

    @FXML
    private Button comprar;

    @FXML
    private Label descuentoText;

    @FXML
    private Label listado;

    @FXML
    private Label precioResultado;

    // Guardamos el cliente, el nombre del cine y las sesiones que queremos comprar,
    // además cargamos las entradas existentes en para controlar el ID de las mismas
    public static void setObjetos(Cliente client, Funcion[] funcions, String cineName) throws SQLException {
        cliente = client;
        compras = funcions;
        cine = cineName;
        entradas = DAO.cargarEntradas();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // ponemos el contador a cero
        contador = 0;
        // Creamos una variable para concatenar todas la sesiones, una en cada linea
        String sesiones = "";
        for (int i = 0; i < compras.length; i++) {
            if (compras[i] != null) {
                sesiones = sesiones + compras[i].toString() + "\n";
                // sumamos una por cada sesión
                contador++;
            }
        }
        // y la escribimos
        listado.setText(sesiones);
        // Ponemos el precio estandar de una entrada
        double precio = 7.5;
        if (contador == 2) { // si hay dos sesiones
            // calculamos el descuento del 20%
            double descuento = precio * contador * 20 / 100;
            // y lo aplicamos
            precio = (precio * contador) - descuento;
            // Por último ponemos el mensaje del descuento aplicado
            descuentoText.setText("Con la compra de 2 entradas tienes un 20% de descuento");
        } else if (contador >= 3) { // si hay más de 3 sesiones
            // calculamos el descuento del 30%
            double descuento = precio * contador * 30 / 100;
            // y lo aplicamos
            precio = (precio * contador) - descuento;
            // Por último ponemos el mensaje del descuento aplicado
            descuentoText.setText("Con la compra de " + contador + " entradas tienes un 30% de descuento");
        }
        // Ponemos el precio definitivo en su Label
        precioResultado.setText(String.valueOf(precio));
    }

    // Mensaje de confirmación de compra
    @FXML
    private void mostrarAlertConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Compra realizada");
        alert.setContentText("¡Compra realizada correctamente! Esperemos que vuelva pronto.");
        alert.showAndWait();
    }

    @FXML
    void comprar(ActionEvent event) throws SQLException, IOException {
        // Empezamos con el ID al uno
        int id = 1;
        // y sumamos por cada entrada existente en la BBDD, obteniendo el último ID
        for (int i = 0; i < entradas.length; i++) {
            if (entradas[i] != null) {
                if (entradas[i].getCliente() == null) {
                    id++;
                }
            }
        }
        // Mientras haya sesiones en el array
        while (contador != 0) {
            // Creamos la fecha para guardarla en la BBDD
            Date fecha = new Date();
            // Creamos un objeto de entrada y guardamos el último id, el cliente que se ha
            // pasado de la vista anterior, la última sesion del array y el precio de 7,5
            Entrada entrada = new Entrada(id, fecha, cliente, compras[contador - 1], 7.5);
            // Guardamos esa entrada en la BBDD
            DAO.insertEntrada(entrada);
            // Sumamos uno para conseguir el siguiente ID
            id++;
            // Y bajamos uno del contador para conseguir la sesion anterior del array
            contador--;
        }
        // Cuando guardemos todas las entradas aparecera la confirmación de la compra
        mostrarAlertConfirmacion();
        // Pasaremos a la vista del ticket y mandaremos las variables cine, cliente y el
        // array de las sesiones que acabamos de comprar
        TicketController.setObjetos(cliente, compras, cine);
        Main.setRoot("Ticket");
    }

    // Metodo que te devuelve a la ventana de los cines y reinicia todo
    @FXML
    void reiniciar(ActionEvent event) throws IOException {
        CinesController.setReinicio(true);
        Main.setRoot("Cines");
    }

}