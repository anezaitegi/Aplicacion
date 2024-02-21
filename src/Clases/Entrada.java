package Clases;

import java.util.Date;

public class Entrada {
    // Atributos de la clase Entrada
    private int id;
    private Date fecha;
    private Cliente cliente;
    private Funcion funcion;
    private double precio;

    // Constructor de todos los atributos
    public Entrada(int id, Date fecha, Cliente cliente, Funcion funcion, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.funcion = funcion;
        this.precio = precio;
    }

    // Getters de los atributos
    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public double getPrecio() {
        return precio;
    }

}
