package Clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    // La dirección de la base de datos en el servidor
    // junto con el usuario de pleno acceso y su respectiva contraseña
    private static String url = "jdbc:mysql://3.76.212.178:3306/coquettecines";
    private static String user = "test";
    private static String pass = "Yta12345678*";

    // La conexción con la BBDD
    public static Connection con = openConnection();

    public static Connection openConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Consulta básica de la tabla que se pase al metodo
    private static String infoTabla(String tabla) {
        return "Select * from " + tabla;
    }

    // Consulta de las pelis disponibles para el cine elegido
    private static String queryPeli() {
        return "select distinct p.* from funcion f natural join sala s inner join pelicula p on f.Pelicula_Codigo = p.Pelicula_Codigo inner join cine c on s.Cine_Codigo = c.Cine_Codigo where c.Nombre = ?";
    }

    // Consulta de las sesiones disponibles para la peli elegida
    private static String queryFuncion() {
        return "select s.Nombre, s.Sala_Codigo, f.Funcion_Codigo, f.Fecha, f.Horario from funcion f natural join sala s inner join pelicula p on f.Pelicula_Codigo = p.Pelicula_Codigo inner join cine c on s.Cine_Codigo = c.Cine_Codigo where c.Nombre = ? and p.Pelicula_Codigo = ?";
    }

    // Frases hechas para insertar clientes y entradas en la BBDD
    private static String insertCliente() {
        return "insert into cliente values (?, ?, ?, ?, ?, ?)";
    }

    private static String insertEntrada() {
        return "insert into entrada values (?, ?, ?, ?, ?)";
    }

    // Metodo que le das el cine y la pelicula y te devuelce las sesiones
    // disponibles en un array de la clase Funcion
    public static Funcion[] cargarFunciones(String cine, Pelicula peli) throws Exception {
        PreparedStatement st = con.prepareStatement(queryFuncion());
        st.setString(1, cine);
        st.setInt(2, peli.getId());
        ResultSet rs = st.executeQuery();
        Funcion[] listaFunciones = new Funcion[10];
        int contador = 0;
        while (rs.next()) {
            Sala sala = new Sala(rs.getInt(2), rs.getString(1));
            listaFunciones[contador] = new Funcion(rs.getInt(3), sala, peli, rs.getDate(4), rs.getTime(5));
            contador++;
        }
        return listaFunciones;
    }

    // Metodo que le das el cine y te devuelve las peliculas disponibles en un array
    // de la clase Pelicula
    public static Pelicula[] cargarPeliculas(String cine) throws Exception {
        PreparedStatement st = con.prepareStatement(queryPeli());
        st.setString(1, cine);
        ResultSet rs = st.executeQuery();
        Pelicula[] listaPeliculas = new Pelicula[10];
        int contador = 0;
        while (rs.next()) {
            listaPeliculas[contador] = new Pelicula(rs.getInt(1), rs.getString(4), rs.getString(6), rs.getString(7),
                    rs.getInt(2), rs.getInt(3), rs.getString(5));
            contador++;
        }
        return listaPeliculas;
    }

    // Metodo que te devuelve los clientes en un array de la clase Cliente
    public static Cliente[] cargarClientes() throws Exception {
        PreparedStatement st = con.prepareStatement(infoTabla("cliente"));
        ResultSet rs = st.executeQuery();
        Cliente[] listaClientes = new Cliente[100];
        int contador = 0;
        while (rs.next()) {
            listaClientes[contador] = new Cliente(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(2), rs.getString(6));
            contador++;
        }
        return listaClientes;
    }

    // Metodo que te devuelve las entradas en un array de la clase Entrada
    public static Entrada[] cargarEntradas() throws SQLException {
        PreparedStatement st = con.prepareStatement(infoTabla("entrada"));
        ResultSet rs = st.executeQuery();
        Entrada[] listaEntradas = new Entrada[500];
        int contador = 0;
        while (rs.next()) {
            listaEntradas[contador] = new Entrada(rs.getInt(1), rs.getDate(3), null, null, rs.getDouble(4));
            contador++;
        }
        return listaEntradas;
    }

    // Metodo que te devuelve los cines en un array de la clase Cine
    public static Cine[] cargarCine() throws SQLException {
        PreparedStatement st = con.prepareStatement(infoTabla("cine"));
        ResultSet rs = st.executeQuery();
        Cine[] listaCines = new Cine[3];
        int contador = 0;
        while (rs.next()) {
            listaCines[contador] = new Cine(rs.getInt(1), rs.getString(2), rs.getString(3), null);
            contador++;
        }
        return listaCines;
    }

    // Metodo que inserta un nuevo cliente en la BBDD con toda su información
    public static void insertCliente(Cliente client) throws SQLException {
        PreparedStatement st = con.prepareStatement(insertCliente());
        st.setString(1, client.getDNI());
        st.setString(2, client.getPassword());
        st.setString(3, client.getNombre());
        st.setString(4, client.getApellido());
        st.setString(5, client.getSexo());
        st.setString(6, client.getTelefono());
        st.execute();
    }

    // Metodo que inserta una nueva entrada en la BBD con toda su información
    public static void insertEntrada(Entrada entrada) throws SQLException {
        PreparedStatement st = con.prepareStatement(insertEntrada());
        st.setInt(1, entrada.getId());
        st.setInt(2, entrada.getFuncion().getId());
        java.sql.Date date = new Date(entrada.getFecha().getTime());
        st.setDate(3, date);
        st.setDouble(4, entrada.getPrecio());
        st.setString(5, entrada.getCliente().getDNI());
        st.execute();
    }
}