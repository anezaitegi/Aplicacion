import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Clases.Cliente;
import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable {

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

    @FXML
    private TextField campoPassword;

    @FXML
    private ImageView imgPassword;

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
    public void initialize(URL location, ResourceBundle resources) {
        Dni.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        pass.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        campoPassword.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        campoPassword.setDisable(true);
    }

    @FXML
    void verificar(ActionEvent event) throws IOException, SQLException {
        String login;
        String password;
        login = Dni.getText();
        password = encriptar(pass.getText());
        System.out.println(password);
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

    @FXML
    void mostrarPassword(MouseEvent event) {
        campoPassword.setDisable(false);
        campoPassword.setText(pass.getText());
        pass.clear();
        pass.setDisable(true);
    }

    @FXML
    void ocultarPassword(MouseEvent event) {
        pass.setDisable(false);
        pass.setText(campoPassword.getText());
        campoPassword.clear();
        campoPassword.setDisable(true);
    }

    @FXML
    private String encriptar(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
