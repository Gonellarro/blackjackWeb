package modelo.datos;

import java.sql.*;
import java.util.*;
import modelo.Usuari;

public class UsuariDaoJDBC {

    private static final String SQL_SELECT = "SELECT idUsuari, nom, llinatges, correu, contrasenya"
            + " FROM usuaris";

    private static final String SQL_SELECT_BY_ID = "SELECT idUsuari, nom, llinatges, correu, contrasenya "
            + " FROM usuaris WHERE idUsuari = ?";

    private static final String SQL_SELECT_BY_EMAIL = "SELECT idUsuari, nom, llinatges, correu, contrasenya "
            + " FROM usuaris WHERE correu = ?";

    private static final String SQL_INSERT = "INSERT INTO usuaris(nom, llinatges, correu, contrasenya) "
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE usuaris "
            + " SET nom=?, llinatges=?, correu=? WHERE idUsuari=?";

    private static final String SQL_DELETE = "DELETE FROM usuaris WHERE idUsuari = ?";

    public List<Usuari> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuari usuari = null;
        List<Usuari> usuaris = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idUsuari = rs.getInt("idUsuari");
                String nom = rs.getString("nom");
                String llinatges = rs.getString("llinatges");
                String correu = rs.getString("correu");
                String contrasenya = rs.getString("contrasenya");

                usuari = new Usuari(idUsuari, nom, llinatges, correu, contrasenya);
                usuaris.add(usuari);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuaris;
    }

    public Usuari encontrar(Integer id_usuari) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuari usuari = new Usuari();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id_usuari);
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            String nom = rs.getString("nom");
            String llinatges = rs.getString("llinatges");
            String correu = rs.getString("correu");
            String contrasenya = rs.getString("contrasenya");

            usuari.setNom(nom);
            usuari.setLlinatges(llinatges);
            usuari.setCorreu(correu);
            usuari.setContrasenya(contrasenya);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuari;
    }

    public static Usuari encontrarCorreo(String correu) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuari usuari = new Usuari();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_EMAIL);
            stmt.setString(1, correu);
            rs = stmt.executeQuery();
            if (rs.absolute(1)) {
                //Existeix el correu de l'usuari
                rs.absolute(1);//nosç posicionamos en el primer registro devuelto
                Integer idUsuari = rs.getInt("idUsuari");
                String nom = rs.getString("nom");
                String llinatges = rs.getString("llinatges");
                //String correu = rs.getString("correu");
                String contrasenya = rs.getString("contrasenya");

                usuari.setIdUsuari(idUsuari);
                usuari.setNom(nom);
                usuari.setLlinatges(llinatges);
                usuari.setCorreu(correu);
                usuari.setContrasenya(contrasenya);

                //System.out.println("Usuari: " + usuari);
                //System.out.println("mail: " + correu);
            } else {
                //No existeix el correu de l'usuari
                //No fa falta fer res més, ja que el missatge d'error és el que toca
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //System.out.println("mail: " + correu);

            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return usuari;
    }

    public int insertar(Usuari usuari) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuari.getNom());
            stmt.setString(2, usuari.getLlinatges());
            stmt.setString(3, usuari.getCorreu());
            stmt.setString(4, usuari.getContrasenya());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Usuari usuari) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuari.getNom());
            stmt.setString(2, usuari.getLlinatges());
            stmt.setString(3, usuari.getCorreu());
            stmt.setString(4, usuari.getContrasenya());
            stmt.setInt(6, usuari.getIdUsuari());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Usuari usuari) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuari.getIdUsuari());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}
