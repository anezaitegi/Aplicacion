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

    // Variable con el nombre del cine, un array de la clase Funcion con las
    // sesiones elegidas para comprar y un array de la clase Cliente para guardar
    // los clientes de la BBDD
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

    // Metodo para guardar las Funciones compradas en el array y el nombre del cine
    // en la variable, además del array los clientes pasado por la vista anterior
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
        // Poner el estilo de los textField
        Dni.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        pass.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        campoPassword.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        // Inhabilitar el TextArea de la contraseña para poder elegir el PasswordArea
        // solamente
        campoPassword.setDisable(true);
    }

    // Funciones para que al pasar el cursor por la imagen del ojo se vea la
    // contraseña
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
    void verificar(ActionEvent event) throws IOException, SQLException {
        // Se guarda el DNI en una variable y la contraseña en otra después de
        // encriptarla, por supuesto
        String login = Dni.getText();
        String password = encriptar(pass.getText());
        // Creamos dos buleanos distintos para saber si el usuario es incorrecto o si no
        // existe
        boolean incorrecto = false;
        boolean noExiste = false;
        // Comprobamos el usuario y la contraseña
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) { // Si el cliente no es null
                if (login.equalsIgnoreCase(clientes[i].getDNI()) // y si coinciden
                        && password.equals(clientes[i].getPassword())) {
                    // mostramos la alerta
                    mostrarAlertConfirmacion();
                    // "apagamos" el buleano de que no existe
                    noExiste = false;
                    // pasamos a la vista del carrito y pasamos el cliente en concreto,
                    // el nombre del cine y el array de las sesiones compradas
                    CarritoController.setObjetos(clientes[i], compras, cine);
                    Main.setRoot("Carrito");
                    break;
                } else if (login.equalsIgnoreCase(clientes[i].getDNI())) { // si existe el DNI
                    // "apagamos" el buleano de que no existe y "encendemos" el de incorrecto
                    incorrecto = true;
                    noExiste = false;
                    break;
                } else { // si el DNI no existe
                    // "encendemos" el buleano de que no existe
                    noExiste = true;
                }
            }
        }
        if (incorrecto) { // si es incorrecto
            // mostramos la alerta y vaciamos los TextFields
            mostrarAlertError();
            Dni.clear();
            pass.clear();
        }
        if (noExiste) { // si el DNI no existe
            // mostramos la alerta y pasamos a la vista de registrarse
            mostrarAlertNoExiste();
            Main.setRoot("Registrate");
        }
    }

    // Metodo para volver a la página para registrarse
    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Registrate");
    }

    // Metodo para encriptar la contraseña
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