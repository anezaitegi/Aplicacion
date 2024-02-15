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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ticketsController implements Initializable {

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
    private Label infoCliente;

    @FXML
    private Label infoEntradas;

    @FXML
    private Button inicio;

    public static void setObjetos(Cliente client, Funcion[] funcions, String cineName) throws SQLException {
        cliente = client;
        compras = funcions;
        cine = cineName;
        listaCines = DAO.cargarCine();
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        Date fecha = new Date();
        String sesiones = "";
        for (int i = 0; i < compras.length; i++) {
            if (compras[i] != null) {
                sesiones = sesiones + compras[i].toString() + "\n";
            }
        }
        infoEntradas.setText(sesiones);
        for(int i=0; i < listaCines.length; i++) {
            if (listaCines[i].getNombre().equals(cine)) {
                infoCine.setText(listaCines[i].toString());
            }
        }
        infoCliente.setText(cliente.toString());  
        Fecha.setText(fecha.toString());      
    }

    @FXML
    void guardarTXT(ActionEvent event) {
        String ruta = "C:\\Users\\Usuario\\Desktop\\Reto3\\Aplicacion\\src\\Tickets.txt";
        try {
            BufferedWriter escribir = new BufferedWriter(new FileWriter(ruta, true));
            escribir.newLine();
            escribir.write(infoEntradas.getText());
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
        guardar.setDisable(true);
    }

    @FXML
    void startAgain(ActionEvent event) throws IOException {
        Main.setRoot("Bienvenida");
    }

}
