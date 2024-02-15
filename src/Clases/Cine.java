package Clases;

public class Cine {
    private int id;
    private String nombre;
    private String direccion;
    private Sala listaSalas[];

    public Cine() {
    }

    public Cine(int id, String nombre, String direccion, Sala[] listaSalas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.listaSalas = listaSalas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre.length() <= 50) {
            this.nombre = nombre;
        } else {
            throw new Exception("Texto demasiado largo");
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) throws Exception {
        if (direccion.length() <= 200) {
            this.direccion = direccion;
        } else {
            throw new Exception("Texto demasiado largo");
        }
    }

    public Sala[] getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(Sala[] listaSalas) {
        this.listaSalas = listaSalas;
    }

    @Override
    public String toString() {
        return nombre + "\n" + direccion;
    }

}
