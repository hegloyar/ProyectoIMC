<%-- 
    Document   : bienvenida
    Created on : Mar 11, 2024, 2:05:37 PM
    Author     : Hector.Galvan
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.app.modelo.Usuario, com.app.modelo.CalculoIMC" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Bienvenida</title>
</head>
<body>
    
    <c:if test="${not empty mensaje}">
        <p style="color: red;">${mensaje}</p>
    </c:if>
 
    <h1>Bienvenido, ${usuario.nombreCompleto}!</h1>
    <p>Fecha de hoy: ${fechaHoy}</p>

    <h2>Historial de IMC</h2>
    <table border="1">
        <tr>
            <th>Fecha</th>
            <th>IMC</th>
            <th>Estado Nutricional</th>
        </tr>
        <c:forEach items="${historialIMC}" var="registro">
            <tr>
                <td><c:out value="${registro.fecha}"/></td>
                <td><c:out value="${registro.imc}"/></td>
                <td><c:out value="${registro.estadoNutricional}"/></td>
            </tr>
        </c:forEach>
    </table>
    
    <h3>Calcular IMC</h3>
    <form action="CalcularIMCServlet" method="post">
        <label for="peso">Peso (kg):</label>
        <input type="number" id="peso" name="peso" step="0.1" required>
        <br>
        <label for="estatura">Estatura (m):</label>
        <input type="number" id="estatura" name="estatura" step="0.01" required>
        <br>
        <button type="submit">Calcular IMC</button>
    </form>
    
    <form action="CerrarSesionServlet" method="post">
        <button type="submit">Cerrar sesión</button>
    </form>
    
</body>
</html>
