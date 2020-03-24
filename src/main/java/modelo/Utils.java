package modelo;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import modelo.datos.UsuariDaoJDBC;
import modelo.Usuari;

public class Utils {

    public static String SHA1(String value) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String sha1 = "";

        // With the java libraries
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(value.getBytes("utf8"));
        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));

        return sha1;

    }

    public static int cookieId(String cookieName, HttpServletRequest request) {
        int cookieValue = 0;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookieValue = Integer.parseInt(cookie.getValue());
                }
            }
        }
        return cookieValue;
    }

    public static boolean comprovaCookie(String cookieName, HttpServletRequest request) {
        boolean resultat = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    resultat = true;
                }
            }
        }
        return resultat;
    }

    public static String collirCookie(String cookieName, HttpServletRequest request) {
        String resultat = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    resultat = cookie.getValue();
                }
            }
        }
        return resultat;
    }

    public static boolean comprovaContrasenya(String correu, String contrasenya) {
        boolean resultat = false;

        Usuari usuari = new UsuariDaoJDBC().encontrarCorreo(correu);
        try {
            if (usuari.getContrasenya().equals(SHA1(contrasenya))) {
                System.out.println("Contrasenya DB" + usuari.getContrasenya());
                System.out.println("Contrasenya PAR" + SHA1(contrasenya));
                resultat = true;
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(System.out);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.out);
        }
        return resultat;
    }

}
