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

    // Variable con el nombre del cine, un array de la clase Funcion con las
    // sesiones elegidas para comprar y un array de la clase Cliente para guardar
    // los clientes de la BBDD
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

    // Metodo para guardar las Funciones compradas en el array y el nombre del cine
    // en la variable, además de cargar los clientes de la BBDD en el array
    public static void setFuncion(Funcion[] elegidos, String cineName) throws Exception {
        compras = elegidos;
        cine = cineName;
        clientes = DAO.cargarClientes();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Poner como opciones del ComboBox Mujer y Hombre y preseleccionar Mujer
        sexo.getItems().addAll("Mujer", "Hombre");
        sexo.setValue("Mujer");
        // Poner el estilo de los textField
        dni.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        nombre.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        apellido.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        password.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        contacto.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgb(0, 0, 0, 0); -fx-border-color: white; -fx-border-radius: 5px;");
        sexo.setStyle(
                "-fx-text-fill: white; -fx-background-color: rgba(240, 248, 255, 0.617); -fx-border-color: white; -fx-border-radius: 5px;");
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

    @FXML
    private void mostrarAlertConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Acceso concedido");
        alert.setContentText("¡Bienvenido! Usuario registrado");
        alert.showAndWait();
    }

    // Metodo para ir al Login pasando el array de Funciones y el de Clientes más el
    // nombre del cine
    @FXML
    void irLogin(ActionEvent event) throws IOException {
        LoginController.setObjetos(compras, clientes, cine);
        Main.setRoot("Login");
    }

    @FXML
    void validar(ActionEvent event) throws Exception {
        // Se guarda cada información en una variable distinta
        String DNI = dni.getText();
        String name = nombre.getText();
        String surname = apellido.getText();
        String sex = sexo.getValue();
        String passWord = password.getText();
        String phone = contacto.getText();
        // Creamos un buleano que nos permita saber si la información metida es correcta
        boolean correcto = true;
        // Comprueba si el DNI existe y si es así "apaga" el buleano, muestra una alerta
        // y pasa a la vista del Login para utilizar ese usuario y pasa los arrays de
        // Funcion y Clientes además del nombre del cine
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
        // Se crea un nuevo cliente y se mete la información poco a poco, para ver si
        // hay algun error, si es así se "apaga" el buleano y se borra el TextArea del
        // dato incorrecto
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
        // Si todo esta bien,
        if (correcto) {
            for (int i = 0; i < clientes.length; i++) {
                if (clientes[i] == null) {
                    // mete el cliente en el array,
                    clientes[i] = client;
                    // se inserta el mismo cliente en la BBDD,
                    DAO.insertCliente(client);
                    // se muestra una alerta de confirmación
                    mostrarAlertConfirmacion();
                    // y pasa a la vista del Carrito, al que le pasan los datos del cliente, el
                    // nombre del cine y el array de Funciones
                    CarritoController.setObjetos(client, compras, cine);
                    Main.setRoot("Carrito");
                    break;
                }
            }
        } else { // Si no esta bien muestra una alerta de error
            mostrarAlertIncorrecto();
        }
    }

    // Metodo para volver a la vista Cines
    @FXML
    void volver(ActionEvent event) throws IOException {
        Main.setRoot("Cines");
    }

}