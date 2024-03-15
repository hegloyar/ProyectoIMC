<%-- 
    Document   : login
    Created on : 10 mar. 2024, 16:34:34
    Author     : galva
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Iniciar Sesión</title>
    </head>
    <body>
        <h2>Iniciar Sesión</h2>
        <form action="LoginServlet" method="post">
            Nombre de usuario: <input type="text" name="nombreUsuario"><br>
            Contraseña: <input type="password" name="password"><br>
            <input type="submit" value="Iniciar Sesión">
        </form>
    </body>
</html>
