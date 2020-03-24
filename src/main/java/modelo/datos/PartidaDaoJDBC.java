package modelo.datos;

import java.sql.*;
import java.util.*;
import modelo.Partida;

public class PartidaDaoJDBC {

    private static final String SQL_SELECT = "SELECT idPartida, idJugador, puntsGuanyador, puntsJugador, blackJackJugador, blackJackIA,"
            + " jugadorGuanyador, IAGuanyador FROM partides";
    private static final String SQL_INSERT = "INSERT INTO partides (idJugador , puntsGuanyador, puntsJugador, blackJackJugador, blackJackIA,"
            + " jugadorGuanyador, IAGuanyador) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE partides SET puntsGuanyador=?, puntsJugador=?, blackJackJugador=?,"
            + " blackJackIA=?, jugadorGuanyador=?, IAGuanyador=? WHERE idPartida=?";

    
    public List<Partida> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Partida partida = null;
        List<Partida> partides = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idPartida = rs.getInt("idPartida");
                System.out.println("listar IdPartida: " + idPartida);
                int idJugador = rs.getInt("idJugador");
                int puntsGuanyador = rs.getInt("puntsGuanyador");
                int puntsJugador = rs.getInt("puntsJugador");
                boolean blackJackJugador = rs.getBoolean("blackJackJugador");
                boolean blackJackIA = rs.getBoolean("blackJackIA");
                boolean jugadorGuanyador = rs.getBoolean("jugadorGuanyador");
                boolean IAGuanyador = rs.getBoolean("IAGuanyador");

                partida = new Partida(idPartida, idJugador, puntsGuanyador, puntsJugador, blackJackJugador, blackJackIA, jugadorGuanyador, IAGuanyador);
                partides.add(partida);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return partides;
    }

    public static void insertar(Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int row = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, partida.getIdJugador());
            stmt.setInt(2, partida.getPuntsGuanyador());
            stmt.setInt(3, partida.getPuntsJugador());
            stmt.setBoolean(4, partida.isBlackJackJugador());
            stmt.setBoolean(5, partida.isBlackJackIA());
            stmt.setBoolean(6, partida.isJugadorGuanyador());
            stmt.setBoolean(7, partida.isIAGuanyador());

            row = stmt.executeUpdate();
            //Si consegueix insertar, posarem a la partida el ID de la fila dins la BD
            if (row > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    partida.setIdPartida((int)generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
    }

    public int actualizar(Partida partida) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            System.out.println("DAO-PuntsGuanyador: " + partida.getPuntsGuanyador());
            System.out.println("DAO-idPartida: " + partida.getIdPartida());
            stmt.setInt(1, partida.getPuntsGuanyador());
            stmt.setInt(2, partida.getPuntsJugador());
            stmt.setBoolean(3, partida.isBlackJackJugador());
            stmt.setBoolean(4, partida.isBlackJackIA());
            stmt.setBoolean(5, partida.isJugadorGuanyador());
            stmt.setBoolean(6, partida.isIAGuanyador());
            stmt.setInt(7, partida.getIdPartida());
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
