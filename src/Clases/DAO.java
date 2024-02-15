package Clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    private static String url = "jdbc:mysql://localhost:3306/cinescoquette";
    private static String user = "root";
    private static String pass = "Admin1234";

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

    public static Connection con = openConnection();

    private static String infoTabla(String tabla) {
        return "Select * from " + tabla;
    }

    private static String queryPeli() {
        return "select distinct p.* from funcion f natural join sala s inner join pelicula p on f.Pelicula_Codigo = p.Pelicula_Codigo inner join cine c on s.Cine_Codigo = c.Cine_Codigo where c.Nombre = ?";
    }

    private static String queryFuncion() {
        return "select s.Nombre, s.Sala_Codigo, f.Funcion_Codigo, f.Fecha, f.Horario from funcion f natural join sala s inner join pelicula p on f.Pelicula_Codigo = p.Pelicula_Codigo inner join cine c on s.Cine_Codigo = c.Cine_Codigo where c.Nombre = ? and p.Pelicula_Codigo = ?";
    }

    private static String insertCliente() {
        return "insert into cliente values (?, ?, ?, ?, ?, ?)";
    }

    private static String insertEntrada() {
        return "insert into entrada values (?, ?, ?, ?, ?)";
    }

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

    public static Pelicula[] cargarPeliculas(String cine) throws Exception {
        PreparedStatement st = con.prepareStatement(queryPeli());
        st.setString(1, cine);
        ResultSet rs = st.executeQuery();
        Pelicula[] listaPeliculas = new Pelicula[10];
        int contador = 0;
        while (rs.next()) {
            listaPeliculas[contador] = new Pelicula(rs.getInt(1), rs.getString(4), rs.getString(6), rs.getString(7),
                    rs.getInt(2), rs.getInt(3), rs.getString(4));
            contador++;
        }
        return listaPeliculas;
    }

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
