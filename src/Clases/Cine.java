package Clases;

public class Cine {
    // Atributos de la clase Cine
    private int id;
    private String nombre;
    private String direccion;
    private Sala listaSalas[];

    // Constructor con todos los atributos
    public Cine(int id, String nombre, String direccion, Sala[] listaSalas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listaSalas = listaSalas;
    }

    // Getters de los atributos
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Sala[] getListaSalas() {
        return listaSalas;
    }
}
