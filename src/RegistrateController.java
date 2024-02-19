import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Clases.Cliente;
import Clases.DAO;
import Clases.Funcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RegistrateController implements Initializable {

    private static Cliente[] clientes;
    private static Funcion[] compras;
    private static String cine;

    @FXML
    private TextField apellido;

    @FXML
    private Button atras;

    @FXML
    private TextField contacto;

    @FXML
    private PasswordField password;

    @FXML
    private TextField dni;

    @FXML
    private Button existe;

    @FXML
    private TextField nombre;

    @FXML
    private Button registrate;

    @FXML
    private ComboBox<String> sexo;

    @FXML
    private TextField campoPassword;

    @FXML
    private ImageView imgPassword;

    // Mensajes de alerta
    @FXML
    private void mostrarAlertUsuarioExiste() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Usuario existente");
        alert.setContentText("¡Error! Usuario ya existe.");
        alert.showAndWait();
    }

    @FXML
    private void mostrarAlertIncorrecto() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Registro inadecuado");
        alert.setContentText("¡Error! La información que se ha metido no es el adecuado.");
        alert.showAndWait();
    }

    public static void setFuncion(Funcion[] elegidos, String cineName) throws Exception {
        compras = elegidos;
        cine = cineName;
        clientes = DAO.cargarClientes();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        sexo.getItems().addAll("Mujer", "Hombre");
        sexo.setValue("Mujer");
        dni.setStyle("-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        nombre.setStyle("-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        apellido.setStyle("-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        password.setStyle("-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        contacto.setStyle("-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        sexo.setStyle("-fx-text-fill: white; -fx-background-color: rgba(240, 248, 255, 0.617); -fx-border-color: white; -fx-border-radius: 5px;");
        campoPassword.setStyle("-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        campoPassword.setDisable(true);
    }

    @FXML
    void irLogin(ActionEvent event) throws IOException {
        LoginController.setObjetos(compras, clientes, cine);
        Main.setRoot("Login");
    }

    @FXML
    void validar(ActionEvent event) throws Exception {
        String DNI = dni.getText();
        String name = nombre.getText();
        String surname = apellido.getText();
        String sex = sexo.getValue();
        String passWord = password.getText();
        String phone = contacto.getText();
        boolean correcto = true;

        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                if (clientes[i].getDNI().equals(DNI)) {
                    mostrarAlertUsuarioExiste();
                    LoginController.setObjetos(compras, clientes, cine);
                    correcto = false;
                    Main.setRoot("Login");
                }
            }
        }
        Cliente client = new Cliente();
        try {
            client.setDNI(DNI);
        } catch (Exception e) {
            correcto = false;
            dni.clear();
        }
        try {
            client.setNombre(name);
        } catch (Exception e) {
            correcto = false;
            nombre.clear();
        }
        try {
            client.setApellido(surname);
        } catch (Exception e) {
            correcto = false;
            apellido.clear();
        }
        client.setMujer(sex);
        try {
            client.setPassword(passWord);
        } catch (Exception e) {
            correcto = false;
            password.clear();
        }
        try {
            client.setTelefono(phone);
        } catch (Exception e) {
            correcto = false;
            contacto.clear();
        }

        if (correcto) {
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] == null) {
                    clientes[i] = client;
                    CarritoController.setObjetos(client, compras, cine);
                    DAO.insertCliente(client);
                    Main.setRoot("Carrito");
                    break;
                }
            }
        } else {
            mostrarAlertIncorrecto();
        }
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Cines");
    }

    @FXML
    void mostrarPassword(MouseEvent event) {
        campoPassword.setDisable(false);
        campoPassword.setText(password.getText());
        password.clear();
        password.setDisable(true);
    }

    @FXML
    void ocultarPassword(MouseEvent event) {
        password.setDisable(false);
        password.setText(campoPassword.getText());
        campoPassword.clear();
        campoPassword.setDisable(true);
    }

}
