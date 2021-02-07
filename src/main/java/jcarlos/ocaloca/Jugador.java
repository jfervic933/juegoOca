package jcarlos.ocaloca;

import java.util.Objects;
import java.util.Random;

public class Jugador {

    // Atributo de clase
    private static Random r = new Random();
    // Atributos de instancia
    private final String nombre;
    private int casillaActual; // Casilla en la que se encuentra
    private int turnosSinJugar;
    private boolean tiraOtraVez;
    private int tirada; // Para guardar lo que saca en el dado

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    // Tirada del dado. Guarda el valor en el atributo correspondiente
    public void tirarDado() {
        this.tirada = r.nextInt(6) + 1;
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

    public int getCasillaActual() {
        return casillaActual;
    }

    public void setCasillaActual(int casillaActual) {
        this.casillaActual = casillaActual;
    }

    public int getTurnosSinJugar() {
        return turnosSinJugar;
    }

    public void setTurnosSinJugar(int turnosSinJugar) {
        this.turnosSinJugar = turnosSinJugar;
    }

    public boolean TiraOtraVez() {
        return tiraOtraVez;
    }

    public void setTiraOtraVez(boolean tiraOtraVez) {
        this.tiraOtraVez = tiraOtraVez;
    }

    public int getTirada() {
        return tirada;
    }

    public void setTirada(int tirada) {
        this.tirada = tirada;
    }

}
