package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Joc;
import modelo.Utils;
import modelo.Estadistiques;
import modelo.Partida;

@WebServlet("/JocControler")
public class JocControler extends HttpServlet {

    private static Joc joc;
    private static String guanyador;
    private static boolean fi = false;

    public static Joc getJoc() {
        return joc;
    }

    public static String getGuanyador() {
        return guanyador;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Quan comencem, hem de mostrar el menu
        String accio = request.getParameter("accio");
        System.out.println("Accio: " + accio);

        if (accio == null) {
            //Hem de mostrar el menu
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else {
            switch (accio) {
                case "nou":
                    System.out.println("Joc nou");
                    //Cream el javaBean      
                    this.joc = new Joc(request);
                    request.setAttribute("joc", this.joc);
                    request.getRequestDispatcher("joc.jsp").forward(request, response);
                    break;
                case "estadistiques":
                    System.out.println("Estadístiques");
                    List<Partida> partides = new Estadistiques().listar();
                    request.setAttribute("partides", partides);
                    request.getRequestDispatcher("estadistiques.jsp").forward(request, response);
                    break;
                case "sortir":
                    System.out.println("Sortir");
                    //Tancar sessio
                    request.getSession().invalidate();
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    break;
                case "mes":
                    this.fi = this.joc.mesCartes();
                    request.setAttribute("fi", this.fi);
                    request.setAttribute("joc", this.joc);
                    if (!fi) {
                        request.getRequestDispatcher("joc.jsp").forward(request, response);
                    } else {
                        this.joc.ia();
                        //Veim qui ha guanyat
                        guanyador = this.joc.calculaResultat();
                        this.joc.actualitzaPartida(guanyador);
                        request.setAttribute("guanyador", this.guanyador);
                        request.getRequestDispatcher("joc.jsp").forward(request, response);
                    }
                    break;
                case "prou":
                    //Demanam les cartes a la IA                   
                    this.joc.ia();
                    //Veim qui ha guanyat
                    guanyador = this.joc.calculaResultat();
                    this.joc.actualitzaPartida(guanyador);
                    this.fi = true;
                    request.setAttribute("fi", this.fi);
                    request.setAttribute("joc", this.joc);
                    request.setAttribute("guanyador", this.guanyador);
                    request.getRequestDispatcher("joc.jsp").forward(request, response);
                    break;
                case "acabar":
                    request.getRequestDispatcher("menu.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        }
    }

}
