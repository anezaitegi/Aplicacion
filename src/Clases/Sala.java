package Clases;

public class Sala {
    // Atributos de la clase Sala
    private int id;
    private String nombre;

    // Constructor con todos los atributos
    public Sala(int id, String nombre) throws Exception {
        this.id = id;
        if (nombre.length() <= 50) {
            this.nombre = nombre;
        } else {
            throw new Exception("Texto demasiado largo");
        }
    }

    // Getters de los atributos
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    // Funcion toString con el nombre solo para ponerlo en la tabla de las sesiones.
    @Override
    public String toString() {
        return nombre;
    }

}
