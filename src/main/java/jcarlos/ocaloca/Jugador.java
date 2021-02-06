
package jcarlos.ocaloca;

import java.util.Objects;

public class Jugador {
    
    private final String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;       
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    // Consideramos que un jugador es igual a otro si los nombres coinciden
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }
}
