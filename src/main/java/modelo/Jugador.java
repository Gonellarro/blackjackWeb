package modelo;

import java.util.ArrayList;
import modelo.cartes.Carta;

public class Jugador {

    private ArrayList<Carta> ma = new ArrayList<>();
    private int puntuacio;
    private boolean blackJack = false;

    public Jugador() {
        //this.ma = null;
        this.puntuacio = 0;
    }

    public ArrayList<Carta> getMa() {
        return ma;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public boolean getblackJack() {
        return blackJack;
    }

    public void cullCarta(Carta carta) {
        int i;
        this.ma.add(carta);
        //Hem de veure si tenim BlackJack
        System.out.println("Nombre de cartes a la ma: " + this.ma.size());
        if (this.ma.size() == 2) {
            if ((this.ma.get(0).getValor() == 1 && this.ma.get(1).getValor() > 9)
                    || (this.ma.get(1).getValor() == 1 && this.ma.get(0).getValor() > 9)) {
                this.puntuacio = 21;
                this.blackJack = true;
            } else {
                if (carta.getValor() > 10) {
                    this.puntuacio = this.puntuacio + 10;
                } else {
                    this.puntuacio = this.puntuacio + carta.getValor();
                }
            }
        } else {
            //Hem de repassar que un As pot valer 1 o 11
            if (carta.getValor() > 10) {
                this.puntuacio = this.puntuacio + 10;
            } else {
                this.puntuacio = this.puntuacio + carta.getValor();
            }
        }

    }

}
