/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.app.controlador;

import com.app.dao.UsuarioDAO;
import com.app.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    private UsuarioDAO usuarioDao;

    @Override
    public void init() {
        usuarioDao = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recoger los datos del formulario
        String nombreCompleto = request.getParameter("nombreCompleto");
        String nombreUsuario = request.getParameter("nombreUsuario");
        int edad = Integer.parseInt(request.getParameter("edad"));
        char sexo = request.getParameter("sexo").charAt(0);
        float estatura = Float.parseFloat(request.getParameter("estatura"));
        float peso = Float.parseFloat(request.getParameter("peso"));
        String password = request.getParameter("password");
        
        if (estatura < 1.0 || estatura > 2.5 || edad < 15) {
          request.setAttribute("mensaje", "Datos inválidos: estatura debe estar entre 1m y 2.5m, y edad debe ser mayor o igual a 15 años.");
          request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
        return;
    }

        // Crear un nuevo objeto Usuario y setear sus propiedades
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(nombreCompleto);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setEdad(edad);
        usuario.setSexo(sexo);
        usuario.setEstatura(estatura);
        usuario.setPeso(peso);
        usuario.setPassword(password); 

        // Guardar el usuario en la base de datos
        boolean registrado = usuarioDao.registrarUsuario(usuario);

        if (registrado) {
            // Establece el usuario en la sesión y redirige a la página de bienvenida
            request.getSession().setAttribute("usuarioLogueado", usuario);
            response.sendRedirect("BienvenidaServlet");
        } else {
            // Si hubo un problema se envia un mensaje de error y redirigir de nuevo al formulario de registro
            request.setAttribute("mensaje", "Hubo un problema con el registro. Inténtalo de nuevo.");
            request.getRequestDispatcher("/WEB-INF/views/registro.jsp").forward(request, response);
        }
    }
}
