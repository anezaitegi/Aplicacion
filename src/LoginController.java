import java.io.IOException;

import Clases.Cliente;
import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    private static Cliente[] clientes;
    private static Funcion[] compras;

    @FXML
    private TextField Dni;

    @FXML
    private Button atras;

    @FXML
    private Button continuar;

    @FXML
    private TextField pass;

    public static void setObjetos(Funcion[] listaFuncions, Cliente[] listaClientes) {
        clientes = listaClientes;
        compras = listaFuncions;
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
    private void mostrarAlertConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Acceso concedido");
        alert.setContentText("¡Bienvenido! Usuario y contraseña correctos");
        alert.showAndWait();
    }

    @FXML
    void verificar(ActionEvent event) throws IOException {
        String login;
        String password;
        login = Dni.getText();
        password = pass.getText();
        boolean noExiste = false;

        for (int i = 0; i < clientes.length; i++) {
            if (login.equalsIgnoreCase(clientes[i].getDNI()) && password.equals(clientes[i].getPassword())) {
                mostrarAlertConfirmacion();
                Main.setRoot("Carrito");
                noExiste = true;
            }
        }
        if (noExiste) {
            mostrarAlertError();
            Dni.clear();
            pass.clear();
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Registrate");
    }

}
