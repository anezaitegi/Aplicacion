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

public class RegistrateController implements Initializable {

    private static Cliente[] clientes;
    private static Funcion[] compras;

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

    public static void setFuncion(Funcion[] elegidos) throws Exception {
        compras = elegidos;
        clientes = DAO.cargarClientes();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        sexo.getItems().addAll("Mujer", "Hombre");
        sexo.setValue("Mujer");
    }

    @FXML
    void irLogin(ActionEvent event) throws IOException {
        LoginController.setObjetos(compras, clientes);
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
                    LoginController.setObjetos(compras, clientes);
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
                    CarritoController.setObjetos(client, compras);
                    Main.setRoot("Carrito");
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

}
