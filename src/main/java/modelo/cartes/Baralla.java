package modelo.cartes;

import java.util.ArrayList;

public class Baralla {

    private static final int NUM_BARALLES = 1;
    private static final int NUM_VALORS = 13;
    private static final int NUM_PALS = 4;
    private  ArrayList<Carta> cartes = new ArrayList<>();

    public Baralla() {
        int i;
        int j;
        int k;
        String[] pals = {"S", "H", "C", "D"};
        Carta carta;
        for (k = 0; k < NUM_BARALLES; k++) {
            for (i = 1; i < NUM_VALORS + 1; i++) {
                for (j = 0; j < NUM_PALS; j++) {
                   carta = new Carta(pals[j], i);
                   this.cartes.add(carta);
                }
            }
        }
    }

    public void escapcar() {
        int i;
        int valorRandom;
        Carta cartaAux;
        
        for (i=0; i< this.cartes.size(); i++){
            valorRandom = (int) (Math.random()*this.cartes.size());
            cartaAux = new Carta("",0);
            cartaAux = this.cartes.get(valorRandom);
            this.cartes.set(valorRandom, this.cartes.get(i));
            this.cartes.set(i, cartaAux);            
        }
    }
    
    public Carta donarCarta(){
        Carta carta;
        carta = new Carta("",0);
        
        if(this.cartes.size() > 0 ){
            carta = this.cartes.get(0);
            this.cartes.remove(0);
        }       
        return carta;        
    }

}
