package modelo.cartes;

public class Carta {
    private String pal;
    private int valor;
    
    public Carta(String pal, int valor){
        this.pal = pal;
        this.valor = valor;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Carta{" + "pal=" + pal + ", valor=" + valor + '}';
    }
}
