package modelo;

import modelo.datos.PartidaDaoJDBC;

public class Partida {
    private int idPartida;
    private int idJugador;
    private int puntsGuanyador;
    private int puntsJugador;
    private boolean blackJackJugador;
    private boolean blackJackIA;
    private boolean jugadorGuanyador;
    private boolean IAGuanyador;
    
    public Partida(){
        
    }
    
    public Partida(int idPartida, int idJugador, int puntsGuanyador, int puntsJugador, boolean blackJackJugador, boolean blackJackIA, boolean jugadorGuanyador,
            boolean IAGuanyador){
        this.idPartida = idPartida;
        this.idJugador = idJugador;
        this.puntsGuanyador = puntsGuanyador;
        this.puntsJugador = puntsJugador;
        this.blackJackIA = blackJackIA;
        this.blackJackJugador = blackJackJugador;
        this.jugadorGuanyador = jugadorGuanyador;
        this.IAGuanyador = IAGuanyador;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getPuntsGuanyador() {
        return puntsGuanyador;
    }

    public void setPuntsGuanyador(int puntsGuanyador) {
        this.puntsGuanyador = puntsGuanyador;
    }

    public int getPuntsJugador() {
        return puntsJugador;
    }

    public void setPuntsJugador(int puntsJugador) {
        this.puntsJugador = puntsJugador;
    }

    public boolean isBlackJackJugador() {
        return blackJackJugador;
    }

    public void setBlackJackJugador(boolean blackJackJugador) {
        this.blackJackJugador = blackJackJugador;
    }

    public boolean isBlackJackIA() {
        return blackJackIA;
    }

    public void setBlackJackIA(boolean blackJackIA) {
        this.blackJackIA = blackJackIA;
    }

    public boolean isJugadorGuanyador() {
        return jugadorGuanyador;
    }

    public void setJugadorGuanyador(boolean jugadorGuanyador) {
        this.jugadorGuanyador = jugadorGuanyador;
    }

    public boolean isIAGuanyador() {
        return IAGuanyador;
    }

    public void setIAGuanyador(boolean IAGuanyador) {
        this.IAGuanyador = IAGuanyador;
    }

    @Override
    public String toString() {
        return "Partida{" + "idPartida=" + idPartida + ", idJugador=" + idJugador + ", puntsGuanyador=" + puntsGuanyador + ", puntsJugador=" + puntsJugador + ", blackJackJugador=" + blackJackJugador + ", blackJackIA=" + blackJackIA + ", jugadorGuanyador=" + jugadorGuanyador + ", IAGuanyador=" + IAGuanyador + '}';
    }    
}
