<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estadistiques de joc</title>
    </head>
    <body>
        <h1>Estadístiques del joc</h1>
        <table>
            <tr><td>IdJugador</td><td>PuntsJugador</td><td>JugadorGuanyador</td></tr>
            <c:forEach items = "${partides}" var="partida"> 
                <tr><td>${partida.idJugador}</td><td>${partida.puntsJugador}</td><td>${partida.jugadorGuanyador}</tr>
                </c:forEach>
        </table>
        <button onclick="location.href = '${pageContext.request.contextPath}/LoginControler'" type="button">Tornar</button>          
    </body>
</html>
