/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.controlador;

import com.app.dao.CalculoIMCDao;
import com.app.modelo.CalculoIMC;
import com.app.modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hector.Galvan
 */
//Descripción: Servlet que maneja la página de bienvenida que se muestra después del inicio de sesión o registro.
//Métodos principales: doGet(HttpServletRequest request, HttpServletResponse response)

//@WebServlet("/BienvenidaServlet")
public class BienvenidaServlet extends HttpServlet {

    private CalculoIMCDao calculoIMCDao;

    @Override
    public void init() {
        calculoIMCDao = new CalculoIMCDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.setAttribute("fechaHoy", new Date());

            List<CalculoIMC> historialIMC = calculoIMCDao.obtenerHistorico(usuario.getUsuarioId());
            request.setAttribute("historialIMC", historialIMC);

            request.getRequestDispatcher("/WEB-INF/views/bienvenida.jsp").forward(request, response);
        } else {
            response.sendRedirect("LoginServlet");
        }
    }
}