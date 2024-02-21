package Clases;

import java.sql.Time;
import java.util.Date;

public class Funcion {
    // Atributos de la clase Funcion
    private int id;
    private Sala sala;
    private Pelicula peli;
    private Date fecha;
    private Time hora;

    // Constructor con todos los atributos
    public Funcion(int id, Sala sala, Pelicula peli, Date fecha, Time hora) {
        this.id = id;
        this.sala = sala;
        this.peli = peli;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getter del ID
    public int getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return peli.toStringTituloDuracion() + " - " + hora + " - " + fecha + " - " + sala.getNombre() + " - 7,5 euros";
    }

}
