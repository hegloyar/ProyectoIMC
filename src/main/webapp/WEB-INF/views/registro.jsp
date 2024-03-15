<%-- 
    Document   : registro
    Created on : 10 mar. 2024, 16:35:09
    Author     : galva
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registro de Usuario</title>
    </head>
    <body>
        <h2>Registro de Usuario</h2>
        
    <c:if test="${not empty mensaje}">
        <p style="color: red;">${mensaje}</p>
    </c:if>
        
        <form action="RegistroServlet" method="post">
            Nombre completo: <input type="text" name="nombreCompleto"><br>
            Nombre de usuario: <input type="text" name="nombreUsuario"><br>
            Edad: <input type="number" name="edad"><br>
            Sexo: <input type="text" name="sexo"><br>
            Estatura: <input type="number" step="0.01" name="estatura"><br>
            Peso: <input type="number" step="0.01" name="peso"><br>
            Contraseña: <input type="password" name="password"><br>
            <input type="submit" value="Registrarse">
        </form>
    </body>
</html>
