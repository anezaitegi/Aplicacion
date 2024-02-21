package Clases;

public class Pelicula {
    // Atributos de la clase Pelicula
    private int id;
    private String titulo;
    private String descripcion;
    private String genero;
    private int duracion;
    private double coste;
    private String imgURL;

    // Constructor con todos los atributos
    public Pelicula(int id, String titulo, String descripcion, String genero, int duracion, double coste,
            String imgURL) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
        this.duracion = duracion;
        this.coste = coste;
        this.imgURL = imgURL;
    }

    // Getters de los atributos
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public double getCoste() {
        return coste;
    }

    public String getImgURL() {
        return imgURL;
    }

    // Funcion que devuelve el titulo de la pelicula con la duración en minutos
    public String toStringTituloDuracion() {
        return titulo + " " + duracion + " min";
    }

}
