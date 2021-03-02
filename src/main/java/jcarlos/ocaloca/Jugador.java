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
        this.casillaActual = 1;
    }

    // Tirada del dado. Guarda el valor en el atributo correspondiente
    public void tirarDado() {
        this.tirada = r.nextInt(6) + 1;
    }

    // Mira la tirada y mueve a la nueva casilla. Controla el rebote   
    public void mover(int movimiento){
        this.casillaActual+=movimiento;
        // Hay que controlar que no se salga del tablero
        if (this.casillaActual >= Tablero.TOTAL_CASILLAS) {
            // Rebote
            int rebote = this.casillaActual - (Tablero.TOTAL_CASILLAS - 1);
            this.casillaActual = Tablero.TOTAL_CASILLAS - 1 - rebote;
        }
    }

    public boolean ganaPartida() {
        return this.casillaActual == Tablero.TOTAL_CASILLAS - 1;
    }

    public void jugarTurno(Tablero tablero) {
        TipoCasilla tipoCasilla;
        // Si el jugador puede jugar su turno
        if (this.turnosSinJugar == 0) {
            do {
                // Tira dados
                this.tirarDado();
                Vista.informarTirada(this);
                // Mueve al jugador
                this.mover(this.tirada);
                // Informa del movimiento
                Vista.informarProgreso(this);
                // Miro la casilla en la que ha caido
                tipoCasilla = tablero.getCasilla(this.getCasillaActual()).getTipo();
                // Actualiza la casilla actual del jugador
                if (tipoCasilla.getSiguienteMovimiento() > 0) {
                    // Se mueve de nuevo
                    this.mover(tipoCasilla.getSiguienteMovimiento());
                    // Informa del movimiento
                    Vista.informarProgreso(this);
                    // Mira la nueva casilla
                    tipoCasilla = tablero.getCasilla(this.casillaActual).getTipo();
                }
                // Actualizo al jugador según la información de esa casilla
                this.tiraOtraVez = tipoCasilla.isTiradaExtra();
                this.turnosSinJugar = tipoCasilla.getTurnosSinJugar();
            } while (this.tiraOtraVez);
        } else { // No juega esa ronda
            // El pozo tiene 100 turnos sin jugar, es imposible que en 
            // 100 turnos no haya sobrepasado algún jugador el pozo
            this.turnosSinJugar -= 1;
            Vista.informarPierdeTurno(this);
        }
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
