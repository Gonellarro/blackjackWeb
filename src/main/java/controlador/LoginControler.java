package controlador;

import modelo.datos.UsuariDaoJDBC;
import modelo.Usuari;
import modelo.Utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginControler")
public class LoginControler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. Si no té cookie, enviar a login screen
        //2. Si té cookie,
        //  2.1. Si està en sessió, entrar al seu compte sense demanar res
        //  2.2. Si no està en sessió enviar a login screen amb la cookie

        String correu = "";
        boolean novaSessio;

        //Cream o capturam la sessio
        HttpSession session = request.getSession();
        novaSessio = session.isNew();     

        //2. Si té cookie
        if (Utils.comprovaCookie("correu", request)) {
            //Collim la cookie
            correu = Utils.collirCookie("correu", request);
            //2.2. Si no està en sessió
            if (novaSessio) {
                //Enviar a login screen
                //Collim l'usuari i l'enviam a la pàgina del login
                System.out.println("Nova sessio");
                //Posam la variable d'usuari a nivell de sessio
                session.setAttribute("correu", correu);
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } //2.1 Si està en sessió
            else {
                System.out.println("Recuperam sessio");
                //Enviam al servlet de controlador de joc
                //Posam la variable d'usuari a nivell de sessio
                //session.setAttribute("correu", correu);
                request.getRequestDispatcher("menu.jsp").forward(request, response);

            }
        } //1. Si no hi ha cookie, enviar al login screen
        else {
            System.out.println("No hi ha cookie");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Collim usuari i contrasenya
        //la verificam a la BD
        //Si és correcte enviam al servlet jocControler
        //Si no és correcte, enviam un altre pic a login

        System.out.println("Entram al post");
        
        //DEMANAR A MANU
        //HttpSession session = request.getSession();

        //Collim usuari i contrasenya
        String correu = request.getParameter("email");
        String contrasenya = request.getParameter("password");

        System.out.println("mail introduit: " + correu);
        System.out.println("pass introduit: " + contrasenya);

        //Si la contrasenya es correcta
        if (Utils.comprovaContrasenya(correu, contrasenya)) {
            System.out.println("Contrasenya coincident");          
            //Guardam la cookie si no en té
            if (!Utils.comprovaCookie("correu", request)){
                Cookie novaCookie = new Cookie("correu", correu);
                //la cookie se almacenara en el cliente por 1 hora (3600 seg)
                novaCookie.setMaxAge(3600);
                response.addCookie(novaCookie);
                System.out.println("Guardant cookie amb valor: " + correu);
            }            
            //Enviam el control a jocControler
            request.getRequestDispatcher("menu.jsp").forward(request, response);                      
        }
        else{
            //Hem de notificar que no són iguals
            request.setAttribute("errorLogin", "Error usuario/contraseña incorrectos");
            //Hem de retornar al login
            request.getRequestDispatcher("login.jsp").forward(request, response);
            System.out.println("Cap al login un altre pic ja que no coincideixen les contrasenyes");
        }
    }

}
