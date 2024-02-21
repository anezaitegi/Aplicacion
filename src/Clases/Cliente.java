package Clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cliente {
    // Atributos de la clase cliente
    private String DNI;
    private String nombre;
    private String apellido;
    private boolean mujer;
    private String password;
    private String telefono;

    // Constructor vacio
    public Cliente() {
    }

    // Constructor de todos los atributos
    public Cliente(String dNI, String nombre, String apellido, String sexo, String password, String telefono)
            throws Exception {
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

    // Getters y setters de los atributos con sus respectivas restricciones
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dNI) throws Exception {
        String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numeroDNI;
        char letraDNI = dNI.charAt(8);
        numeroDNI = Integer.valueOf(dNI.substring(0, 8));
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
            // Encriptamos la contraseña antes de guardarla
            try {
                MessageDigest m = MessageDigest.getInstance("MD5");
                m.update(password.getBytes());
                byte[] bytes = m.digest();
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                this.password = s.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            throw new Exception("Texto demasiado largo");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono.length() > 0 && telefono.length() < 15) {
            this.telefono = telefono;
        }
    }

    // Funcion que tiene en cada
    @Override
    public String toString() {
        return DNI + "\n" + nombre + " " + apellido + "\n" + telefono;
    }

}
