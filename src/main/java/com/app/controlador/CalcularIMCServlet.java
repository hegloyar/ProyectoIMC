/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.controlador;

import com.app.dao.CalculoIMCDao;
import com.app.dao.UsuarioDAO;
import com.app.modelo.CalculoIMC;
import com.app.modelo.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author galva
 */
//Descripción: Servlet que maneja el cálculo del IMC para usuarios logueados.
//Métodos principales: doPost(HttpServletRequest request, HttpServletResponse response)



//@WebServlet("/CalcularIMCServlet")
public class CalcularIMCServlet extends HttpServlet {
    private CalculoIMCDao calculoIMCDao;

    @Override
    public void init() {
        calculoIMCDao = new CalculoIMCDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");
        // Recoger los datos del formulario
        float peso = Float.parseFloat(request.getParameter("peso"));
        float estatura = Float.parseFloat(request.getParameter("estatura"));
        // Validar la estatura
        if (estatura < 1.0 || estatura > 2.5) {
          request.setAttribute("mensaje", "Datos inválidos: estatura debe estar entre 1m y 2.5m.");
          request.getRequestDispatcher("/WEB-INF/views/bienvenida.jsp").forward(request, response);
        return;
    }
        
        
        float imc = peso / (estatura * estatura);
        String estadoNutricional = UsuarioDAO.determinarEstadoNutricional(imc);

    System.out.println("Peso: " + peso);
    System.out.println("Estatura: " + estatura);
    System.out.println("IMC: " + imc);
    System.out.println("Estado Nutricional: " + estadoNutricional);
        
    // Calcular el IMC y actualizar el historial si los datos son válidos    
        CalculoIMC calculoIMC = new CalculoIMC();
        calculoIMC.setUsuarioId(usuario.getUsuarioId());
        calculoIMC.setFecha(new Date());
        calculoIMC.setImc(imc);
        calculoIMC.setEstadoNutricional(estadoNutricional);

    boolean resultado = calculoIMCDao.agregarCalculoIMC(calculoIMC);
    System.out.println("Resultado de la inserción: " + resultado);    

        response.sendRedirect("BienvenidaServlet");
    }
}
