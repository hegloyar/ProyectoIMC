/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dao;

import com.app.modelo.Usuario;
import com.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author galva
 */
//Descripción: Clase de acceso a datos para las operaciones relacionadas con los usuarios.
//Métodos principales: registrarUsuario(Usuario usuario), autenticarUsuario(String nombreUsuario, String password)

public class UsuarioDAO {

    public static String determinarEstadoNutricional(float imc) {
        if (imc < 18.5) {
            return "Bajo peso";
        } else if (imc >= 18.5 && imc <= 24.9) {
            return "Peso normal";
        } else if (imc >= 25.0 && imc <= 29.9) {
            return "Pre-obesidad o Sobrepeso";
        } else if (imc >= 30.0 && imc <= 34.9) {
            return "Obesidad clase I";
        } else if (imc >= 35.0 && imc <= 39.9) {
            return "Obesidad clase II";
        } else {
            return "Obesidad clase III";
        }
    }

    public boolean registrarUsuario(Usuario usuario) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement psUsuario = con.prepareStatement("INSERT INTO Usuarios (NombreCompleto, NombreUsuario, Edad, Sexo, Estatura, Peso, Password, FechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {

            psUsuario.setString(1, usuario.getNombreCompleto());
            psUsuario.setString(2, usuario.getNombreUsuario());
            psUsuario.setInt(3, usuario.getEdad());
            psUsuario.setString(4, String.valueOf(usuario.getSexo()));
            psUsuario.setFloat(5, usuario.getEstatura());
            psUsuario.setFloat(6, usuario.getPeso());
            psUsuario.setString(7, usuario.getPassword());
            psUsuario.setTimestamp(8, new java.sql.Timestamp(new Date().getTime()));

            int affectedRowsUsuario = psUsuario.executeUpdate();

            if (affectedRowsUsuario == 1) {
                try (ResultSet rs = psUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        int usuarioId = rs.getInt(1);
                        usuario.setUsuarioId(usuarioId);

                        float imc = usuario.getPeso() / (usuario.getEstatura() * usuario.getEstatura());
                        String estadoNutricional = determinarEstadoNutricional(imc);

                        try (PreparedStatement psIMC = con.prepareStatement("INSERT INTO CalculosIMC (UsuarioID, Fecha, IMC, EstadoNutricional) VALUES (?, ?, ?, ?)")) {
                            psIMC.setInt(1, usuarioId);
                            psIMC.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
                            psIMC.setFloat(3, imc);
                            psIMC.setString(4, estadoNutricional);

                            int affectedRowsIMC = psIMC.executeUpdate();
                            return affectedRowsIMC == 1;
                        }
                    }
                }
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario autenticarUsuario(String nombreUsuario, String password) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Usuarios WHERE NombreUsuario = ? AND Password = ?")) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setUsuarioId(rs.getInt("UsuarioID"));
                    usuario.setNombreCompleto(rs.getString("NombreCompleto"));
                    usuario.setNombreUsuario(rs.getString("NombreUsuario"));
                    usuario.setEdad(rs.getInt("Edad"));
                    usuario.setSexo(rs.getString("Sexo").charAt(0));
                    usuario.setEstatura(rs.getFloat("Estatura"));
                    usuario.setPeso(rs.getFloat("Peso"));
                    usuario.setPassword(rs.getString("Password"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}