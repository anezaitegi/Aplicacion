package Clases;

public class Cliente {
    private String DNI;
    private String nombre;
    private String apellido;
    private boolean mujer;
    private String password;
    private String telefono;

    public Cliente() {
    }

    public Cliente(String dNI, String nombre, String apellido, String sexo, String password, String telefono) throws Exception {
        DNI = dNI;
        this.nombre = nombre;
        this.apellido = apellido;
        if (sexo.equals("Mujer")) {
            this.mujer = true;
        } else if (sexo.equals("Hombre")) {
            this.mujer = false;
        } else {
            throw new Exception("Sexo no válido");
        }
        this.password = password;
        this.telefono = telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) throws Exception {
        String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeroDNI;
        char letraDNI = dNI.charAt(8);
        numeroDNI = Integer.valueOf(dNI.substring(0, 7));
        if (NIF_STRING_ASOCIATION.charAt(numeroDNI % 23) == letraDNI) {
            DNI = dNI;
        } else {
            throw new Exception("DNI incorrecto");
        }
        
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellidos) throws Exception {
        if (apellidos.length() <= 150) {
            this.apellido = apellidos;
        } else {
            throw new Exception("Texto demasiado largo");
        }
    }

    public String getSexo() {
        if (mujer) {
            return "Mujer";
        }
        return "Hombre";
    }

    public void setMujer(String sexo) throws Exception {
        if (sexo.equals("Mujer")) {
            this.mujer = true;
        } else if (sexo.equals("Hombre")) {
            this.mujer = false;
        } else {
            throw new Exception("Sexo no válido");
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password.length() <= 255) {
            this.password = password;
        } else {
            throw new Exception("Texto demasiado largo");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
