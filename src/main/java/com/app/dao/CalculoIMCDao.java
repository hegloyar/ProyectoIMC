/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dao;

import com.app.modelo.CalculoIMC;
import com.app.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hector.Galvan
 */
//Descripción: Clase de acceso a datos para las operaciones relacionadas con los cálculos del IMC.
//Métodos principales: obtenerHistorico(int usuarioId), agregarCalculoIMC(CalculoIMC calculoIMC)

public class CalculoIMCDao {

    // Este método recupera el historial de cálculos de IMC para un usuario específico
    public List<CalculoIMC> obtenerHistorico(int usuarioId) {
        List<CalculoIMC> historial = new ArrayList<>();

        String sql = "SELECT CalculoID, UsuarioID, Fecha, IMC, EstadoNutricional FROM CalculosIMC WHERE UsuarioID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, usuarioId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CalculoIMC calculoIMC = new CalculoIMC();
                calculoIMC.setCalculoId(rs.getInt("CalculoID"));
                calculoIMC.setUsuarioId(rs.getInt("UsuarioID"));
                calculoIMC.setFecha(rs.getDate("Fecha"));
                calculoIMC.setImc(rs.getFloat("IMC"));
                calculoIMC.setEstadoNutricional(rs.getString("EstadoNutricional"));

                historial.add(calculoIMC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return historial;
    }

    // Este método añade un nuevo cálculo de IMC a la base de datos para un usuario
    public boolean agregarCalculoIMC(CalculoIMC calculoIMC) {
        String sql = "INSERT INTO CalculosIMC (UsuarioID, Fecha, IMC, EstadoNutricional) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, calculoIMC.getUsuarioId());
            pstmt.setDate(2, new java.sql.Date(calculoIMC.getFecha().getTime()));
            pstmt.setFloat(3, calculoIMC.getImc());
            pstmt.setString(4, calculoIMC.getEstadoNutricional());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows == 1;
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
}