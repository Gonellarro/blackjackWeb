package modelo;

import java.util.List;
import modelo.datos.PartidaDaoJDBC;

public class Estadistiques {

    public Estadistiques() {

    }

    public List<Partida> listar() {
        List<Partida> partides;
        partides = new PartidaDaoJDBC().listar();
        return partides;
    }

}
