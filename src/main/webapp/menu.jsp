<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu del joc</title>
    </head>
    <body>
        <h1>Menu del BlackJack</h1>
        <button onclick="location.href= '${pageContext.request.contextPath}/JocControler?accio=nou'" type="button">Nou Joc</button>
        <button onclick="location.href= '${pageContext.request.contextPath}/JocControler?accio=estadistiques'" type="button">Estadistiques</button>
        <button onclick="location.href= '${pageContext.request.contextPath}/JocControler?accio=sortir'" type="button">Sortir</button>
    </body>
</html>
