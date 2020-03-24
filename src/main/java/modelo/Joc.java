package modelo;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import modelo.cartes.*;
import modelo.datos.UsuariDaoJDBC;
import modelo.datos.PartidaDaoJDBC;
import modelo.Usuari;

public class Joc {

    private Baralla baralla;
    private Jugador jugador;
    private Jugador ia;
    private Usuari usuari;
    private Partida partida;

    public Joc(HttpServletRequest request) {
        int idPartida;
        String correu;

        //Crear Baralla     
        this.baralla = new Baralla();
        //Escapçar Baralla
        this.baralla.escapcar();
        //Cream l'usuari
        correu = Utils.collirCookie("correu", request);
        usuari = new Usuari();
        System.out.println("correu: " + correu);
        usuari = UsuariDaoJDBC.encontrarCorreo(correu);

        //Crear ma del jugador
        //Per això li donam 2 cartes segons les regles i una a la ia
        Carta carta = new Carta("", 0);
        carta = this.baralla.donarCarta();
        this.jugador = new Jugador();
        this.jugador.cullCarta(carta);
        //Llavors li donam una carta a la ia
        this.ia = new Jugador();
        carta = this.baralla.donarCarta();
        this.ia.cullCarta(carta);
        //Finalment, cull la segona carta el jugador
        carta = this.baralla.donarCarta();
        this.jugador.cullCarta(carta);
        //Cream la partida
        partida = new Partida(0, usuari.getIdUsuari(), 0, 0, false, false, false, false);
        new PartidaDaoJDBC().insertar(partida);
        
    }

    public Baralla getBaralla() {
        return baralla;
    }

    public void setBaralla(Baralla baralla) {
        this.baralla = baralla;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getIa() {
        return ia;
    }

    public void setIa(Jugador ia) {
        this.ia = ia;
    }

    public boolean mesCartes() {
        Carta carta = new Carta("", 0);
        boolean resultat = false;

        carta = this.baralla.donarCarta();
        this.jugador.cullCarta(carta);
        System.out.println("Carta: " + carta);
        if (this.jugador.getPuntuacio() > 21) {
            resultat = true;
        }
        return resultat;
    }

    public void ia() {

        Carta carta = new Carta("", 0);
        //La ia té obligació de collir si no té 16 i aturar si té 17
        while (this.ia.getPuntuacio() < 17) {
            carta = this.baralla.donarCarta();
            this.ia.cullCarta(carta);
        }
    }

    public String calculaResultat() {
        String resultat = "Guanya la IA";

        if (this.jugador.getPuntuacio()
                < 22) {
            //Comprovam que el jugador no tingui BlackJack
            if (!this.jugador.getblackJack()) {
                //Comprovam com ha quedat el resultat
                if (this.ia.getPuntuacio() < 22 && this.jugador.getPuntuacio() < this.ia.getPuntuacio()) {
                    resultat = "Guanya la IA";
                } else {
                    resultat = "Guanya el jugador";
                }
            } else {
                if (!this.ia.getblackJack()) {
                    resultat = "Guanya el jugador";
                } else {
                    resultat = "No guanya ningú";
                }
            }
        } else {
            if (this.ia.getPuntuacio() < 22) {
                resultat = "Guanya la IA";
            } else {
                resultat = "No guanya ningú";
            }
        }

        return resultat;
    }

    public void actualitzaPartida(String resultat) {
        int row;
        int puntsGuanyador = 0;
        int puntsJugador = 0;
        boolean jugadorGuanyador = true;
        boolean IAGuanyador = false;        

        System.out.println("Actualitza partida");
        puntsJugador = this.jugador.getPuntuacio();
        puntsGuanyador = this.jugador.getPuntuacio();
        if (resultat.equals("Guanya la IA")) {
            puntsGuanyador = this.ia.getPuntuacio();
            jugadorGuanyador = false;
            IAGuanyador = true;
        }
        
        System.out.println("PuntsJugador: " + puntsJugador);
        System.out.println("PuntsGuanyador: " + puntsGuanyador);
        
        partida.setPuntsGuanyador(puntsGuanyador);
        partida.setPuntsJugador(puntsJugador);
        partida.setJugadorGuanyador(jugadorGuanyador);
        partida.setIAGuanyador(IAGuanyador);
        partida.setBlackJackJugador(this.jugador.getblackJack());
        partida.setBlackJackIA(this.ia.getblackJack());
        row = new PartidaDaoJDBC().actualizar(partida);
    }
}
