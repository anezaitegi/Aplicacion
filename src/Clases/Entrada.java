package Clases;

import java.util.Date;

public class Entrada {
    private int id;
    private Date fecha;
    private Cliente cliente;
    private Funcion listaProyecciones[];
    private double precio;

    public Entrada() {
    }

    public Entrada(int id, Date fecha, Cliente cliente, Funcion[] listaProyecciones, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.listaProyecciones = listaProyecciones;
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

    public Funcion[] getListaProyecciones() {
        return listaProyecciones;
    }

    public void setListaProyecciones(Funcion[] listaProyecciones) {
        this.listaProyecciones = listaProyecciones;
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
