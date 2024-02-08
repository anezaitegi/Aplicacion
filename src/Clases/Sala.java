package Clases;

public class Sala {
    private int id;
    private String nombre;

    public Sala() {
    }

    public Sala(int id, String nombre) throws Exception {
        this.id = id;
        if (nombre.length() <= 50) {
            this.nombre = nombre;
        } else {
            throw new Exception("Texto demasiado largo");
        }
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

    @Override
    public String toString() {
        return nombre;
    }

    

}
