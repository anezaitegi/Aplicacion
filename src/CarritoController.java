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

    private static Cliente cliente;
    private static Funcion[] compras;
    private static Entrada[] entradas;
    private int contador;

    @FXML
    private Button volver;

    @FXML
    private Button comprar;

    @FXML
    private Label listado;

    @FXML
    private Label precioResultado;

    @FXML
    private void mostrarAlertConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Compra realizada");
        alert.setContentText("¡Compra realizada correctamente! Esperemos que vuelva pronto.");
        alert.showAndWait();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        contador = 0;
        double precio = 7.5;
        for (int i = 0; i < compras.length; i++) {
            if (compras[i] != null) {
                listado.setText(compras[i].toString());
                contador++;
            }
        }
        if (contador == 2) {
            precio = precio * contador * 20 / 100;
        } else if (contador >= 3) {
            precio = precio + contador * 30 / 100;
        }
        precioResultado.setText(String.valueOf(precio));
    }


    @FXML
    void comprar(ActionEvent event) throws SQLException, IOException {
        int id = 0;
        entradas = DAO.cargarEntradas();
        for (int i=0; i<entradas.length; i++){
            if (entradas[i].getCliente() == null) {
                id++;
            }else{
                Date fecha = new Date();
                entradas[i] = new Entrada(id, fecha, cliente, compras[contador], 7.5);
                id++;
                contador--;
                if (contador == -1) {
                    break;
                }
            }
        }
        // Falta codigo para guardar entrada.
        mostrarAlertConfirmacion();
        Main.setRoot("tickets");

    }

    @FXML
    void reiniciar(ActionEvent event) throws IOException {
        CinesController.setReinicio(true);
        Main.setRoot("Cines");
    }

}
