package Clases;

import java.util.Date;

public class Proyeccion {
    private int id;
    private Sala sala;
    private Pelicula peli;
    private Date fecha;

    public Proyeccion() {
    }

    public Proyeccion(int id, Sala sala, Pelicula peli, Date fecha) {
        this.id = id;
        this.sala = sala;
        this.peli = peli;
        this.fecha = fecha;
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

}
