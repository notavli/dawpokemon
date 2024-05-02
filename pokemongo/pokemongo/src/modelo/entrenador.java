
package modelo;

import java.util.Objects;


public class entrenador {
    private int id;
    private String name;
    private String contraseña;
//constructores con todo
    public entrenador(int id, String name, String contraseña) {
        this.id = id;
        this.name = name;
        this.contraseña = contraseña;
    }
    
    //contructor con nombre y contraseña

    public entrenador(String name, String contraseña) {
        this.id = 0;
        this.name = name;
        this.contraseña = contraseña;
    }
    //getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
   @Override
    public String toString() {
        return "El entrenador " + name + " tiene el password " + contraseña;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof entrenador)) {
            return false;
        }
        final entrenador other = (entrenador) obj;
        return Objects.equals(this.name, other.name);
    }
    
    
    
}
