<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Joc BalckJack</title>
    </head>
    <body>
        <h1>Jugam a BlackJack</h1>
        <b>Cartes Jugador</b><br>
        <c:forEach items = "${joc.jugador.ma}" var="carta">            
            <img src="resources/img/poker/<c:out value="${carta.valor}"/><c:out value="${carta.pal}"/>.png" width="100" height="130">
        </c:forEach>
        <br>
        Puntuació jugador: ${joc.jugador.puntuacio}
        <c:if test = "${joc.jugador.blackJack}">
            BLACKJACK!
        </c:if> 
        <br><br>
        <c:if test = "${fi}">
            <b>Cartes IA</b><br>
            <c:forEach items = "${joc.ia.ma}" var="carta">               
                <img src="resources/img/poker/<c:out value="${carta.valor}"/><c:out value="${carta.pal}"/>.png" width="100" height="130">
            </c:forEach>
            <br> 
            Puntuacio IA: ${joc.ia.puntuacio}
            <c:if test = "${joc.ia.blackJack}">
                BLACKJACK!
            </c:if> 
            <br><br>
            Resultat: ${guanyador}
        </c:if>        
        <br>
        <button onclick="location.href = '${pageContext.request.contextPath}/JocControler?accio=mes'" type="button" 
                <c:if test = "${fi}">
                    disabled
                </c:if>   
                >Mes cartes</button>
        <button onclick="location.href = '${pageContext.request.contextPath}/JocControler?accio=prou'" type="button"
                <c:if test = "${fi}">
                    disabled
                </c:if>   
                >Prou cartes</button>

        <button onclick="location.href = '${pageContext.request.contextPath}/JocControler?accio=nou'" type="button"
                <c:if test = "${!fi}">
                    disabled
                </c:if>     
                >Tornar a jugar</button>        
        <button onclick="location.href = '${pageContext.request.contextPath}/JocControler?accio=acabar'" type="button"
                <c:if test = "${!fi}">
                    disabled
                </c:if>     
                >Sortir</button>                  
    </body>
</html>
