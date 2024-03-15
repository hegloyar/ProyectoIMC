/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.controlador;

import com.app.dao.CalculoIMCDao;
import com.app.modelo.Usuario;
import com.app.dao.UsuarioDAO;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author galva
 * @WebServlet("/LoginServlet")
 * 
 */

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDao;

    @Override
    public void init() {
        usuarioDao = new UsuarioDAO();
    }

    
    private CalculoIMCDao calculoIMCDao;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDao.autenticarUsuario(nombreUsuario, password);

        if (usuario != null) {
            request.getSession().setAttribute("usuarioLogueado", usuario);
            response.sendRedirect("BienvenidaServlet");
        } else {
            request.setAttribute("mensaje", "Nombre de usuario o contraseña incorrectos.");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
