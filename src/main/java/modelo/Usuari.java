package modelo;


public class Usuari {
    
    private int idUsuari;
    private String nom;
    private String llinatges;
    private String correu;
    private String contrasenya;

    public Usuari() {
    }

    public Usuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public Usuari(String nom, String llinatges, String correu, String contrasenya) {
        this.nom = nom;
        this.llinatges = llinatges;
        this.correu = correu;
        this.contrasenya = contrasenya;
    }

    public Usuari(int idUsuari, String nom, String llinatges, String correu, String contrasenya) {
        this.idUsuari = idUsuari;
        this.nom = nom;
        this.llinatges = llinatges;
        this.correu = correu;
        this.contrasenya = contrasenya;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatges() {
        return llinatges;
    }

    public void setLlinatges(String llinatges) {
        this.llinatges = llinatges;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idUsuari=" + idUsuari + ", nom=" + nom + ", llinatges=" + llinatges + ", correu=" + correu + ", contrasenya=" + contrasenya + '}';
    }
    
}
