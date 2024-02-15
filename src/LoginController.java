import java.io.IOException;
import java.sql.SQLException;

import Clases.Cliente;
import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    private static Cliente[] clientes;
    private static Funcion[] compras;
    private static String cine;

    @FXML
    private TextField Dni;

    @FXML
    private Button atras;

    @FXML
    private Button continuar;

    @FXML
    private PasswordField pass;

    public static void setObjetos(Funcion[] listaFuncions, Cliente[] listaClientes, String cineName) {
        clientes = listaClientes;
        compras = listaFuncions;
        cine = cineName;
    }

    // Mensajes de acceso o no acceso
    @FXML
    private void mostrarAlertError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Acceso denegado");
        alert.setContentText("¡Error! Usuario o contraseña incorrectos");
        alert.showAndWait();
    }

    @FXML
    private void mostrarAlertNoExiste() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Acceso denegado");
        alert.setContentText("¡Error! Usuario no encontrado.");
        alert.showAndWait();
    }

    @FXML
    private void mostrarAlertConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Acceso concedido");
        alert.setContentText("¡Bienvenido! Usuario y contraseña correctos");
        alert.showAndWait();
    }

    @FXML
    void verificar(ActionEvent event) throws IOException, SQLException {
        String login;
        String password;
        login = Dni.getText();
        password = pass.getText();
        boolean incorrecto = false;
        boolean noExiste = false;

        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                if (login.equalsIgnoreCase(clientes[i].getDNI()) && password.equals(clientes[i].getPassword())) {
                    mostrarAlertConfirmacion();
                    CarritoController.setObjetos(clientes[i], compras, cine);
                    noExiste = false;
                    Main.setRoot("Carrito");
                    break;
                } else if (login.equalsIgnoreCase(clientes[i].getDNI())) {
                    incorrecto = true;
                    noExiste = false;
                    break;
                } else {
                    noExiste = true; 
                }
            }

        }
        if (incorrecto) {
            mostrarAlertError();
            Dni.clear();
            pass.clear();
        }
        if (noExiste) {
            mostrarAlertNoExiste();
            Main.setRoot("Registrate");
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Registrate");
    }

}
