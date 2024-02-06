package Clases;

import java.sql.Time;
import java.util.Date;

public class Funcion {
    private int id;
    private Sala sala;
    private Pelicula peli;
    private Date fecha;
    private Time hora;

    public Funcion() {
    }

    public Funcion(int id, Sala sala, Pelicula peli, Date fecha, Time hora) {
        this.id = id;
        this.sala = sala;
        this.peli = peli;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPeli() {
        return peli;
    }

    public void setPeli(Pelicula peli) {
        this.peli = peli;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}
