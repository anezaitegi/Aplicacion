package Clases;

import java.sql.Connection;
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

    private static String queryPeli(String cine) {
        return "select distinct p.* from funcion f natural join sala s inner join pelicula p on f.Pelicula_Codigo = p.Pelicula_Codigo inner join cine c on s.Cine_Codigo = c.Cine_Codigo where c.Nombre = '"
                + cine + "'";
    }

    private static String queryFuncion(int peli, String cine) {
        return "select s.Nombre, s.Sala_Codigo, f.Funcion_Codigo, f.Fecha, f.Horario from funcion f natural join sala s inner join pelicula p on f.Pelicula_Codigo = p.Pelicula_Codigo inner join cine c on s.Cine_Codigo = c.Cine_Codigo where c.Nombre = '"
                + cine + "' and p.Pelicula_Codigo = " + peli;
    }

    public static Funcion[] cargarFunciones(String cine, Pelicula peli) throws Exception {
        PreparedStatement st = con.prepareStatement(queryFuncion(peli.getId(), cine));
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
        PreparedStatement st = con.prepareStatement(queryPeli(cine));
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

}
