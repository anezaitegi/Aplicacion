package Clases;

import java.util.Date;

public class Entrada {
    private int id;
    private Date fecha;
    private Cliente cliente;
    private Funcion funcion;
    private double precio;

    public Entrada() {
    }

    public Entrada(int id, Date fecha, Cliente cliente, Funcion funcion, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.funcion = funcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setListaProyecciones(Funcion funcion) {
        this.funcion = funcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws Exception {
        if (precio > 0) {
            this.precio = precio;
        } else {
            throw new Exception("El precio no puede ser negativo");
        }
    }

}
