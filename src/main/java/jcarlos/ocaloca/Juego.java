package jcarlos.ocaloca;

import java.util.Arrays;

public class Juego {

    private final Tablero tablero;
    private final ControladorJugadores cj;

    // Crea el juego a partir del tablero y la  lista de jugadores
    // Pone a los jugadores en la casilla de salida
    public Juego(Tablero tablero, ControladorJugadores controlador) {
        this.tablero = tablero;
        this.cj = controlador;
        // Coloca a los jugadores en la casilla de salida
        for (Jugador aux : cj.getTodosJugadores()) {
            this.tablero.getCasilla(1).ponerJugador(aux);
        }

    }

    public static void main(String[] args) {

        // Se crea el juego
        String[] nombres = {"J1", "J2", "J3"};
        ControladorJugadores cj = new ControladorJugadores(nombres);
        Tablero tablero = new Tablero();
        Juego juego = new Juego(tablero, cj);

        // Imprime el estado del tablero inicialmente
        Vista.mostrarTablero(juego.getTablero());
        
        Jugador jugador;
        TipoCasilla casillaJugador;

        do {
            // Coge al jugador que le toca
            jugador = cj.getJugadorSiguiente();
            // Guarda el tipo de casilla en la que se encuentra el jugador
            casillaJugador = tablero.getCasilla(jugador.getCasillaActual()).getTipo();
            // Si está en el pozo mira si hay otros por delante
            if ((casillaJugador != TipoCasilla.POZO)
               || ((casillaJugador == TipoCasilla.POZO) && tablero.hayJugadoresDespuesPozo())) {
                // Quita al jugador de la casilla actual del tablero
                juego.getTablero().getCasilla(jugador.getCasillaActual()).quitarJugador(jugador);
                // Juega su turno
                jugador.jugarTurno(tablero);
                // Coloca al jugador en la casilla que le ha tocado
                juego.getTablero().getCasilla(jugador.getCasillaActual()).ponerJugador(jugador);
                // Muestra el resultado
                Vista.mostrarTablero(juego.tablero);
            }

        } while (!jugador.ganaPartida()); // Si el jugador no gana, repite con otro

        System.out.println("\n\n --- El ganador es: " + jugador.getNombre());

    }

    public Tablero getTablero() {
        return tablero;
    }

    public ControladorJugadores getCj() {
        return cj;
    }

}
