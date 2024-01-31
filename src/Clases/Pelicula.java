package Clases;

public class Pelicula {
    private int id;
    private String titulo;
    private String descripcion;
    private String genero;
    private int duracion;
    private double coste;
    private String imgURL;

    public Pelicula() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws Exception {
        if (titulo.length() <= 200) {
            this.titulo = titulo;
        } else {
            throw new Exception("Texto demasiado largo");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) throws Exception {
        if (genero.equals("Drama") || genero.equals("Ciencia") || genero.equals("Comedia") || genero.equals("Terror")) {
            this.genero = genero;
        } else {
            throw new Exception("Genero no disponible");
        }
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) throws Exception {
        if (duracion > 0) {
            this.duracion = duracion;
        } else {
            throw new Exception("La duración no puede ser negativa");
        }
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) throws Exception {
        if (coste > 0) {
            this.coste = coste;
        } else {
            throw new Exception("El coste no puede ser negativo");
        }
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) throws Exception {
        if (imgURL.length() <= 300) {
            this.imgURL = imgURL;
        } else {
            throw new Exception("URL demasiado largo");
        }
    }

}
