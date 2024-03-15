/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.modelo;
import java.util.Date;

/**
 *
 * @author Hector.Galvan
 */
//Descripción: Representa un cálculo del Índice de Masa Corporal (IMC) para un usuario.
//Métodos principales: getCalculoId(), setCalculoId(), getImc(), setImc(), getEstadoNutricional(), setEstadoNutricional()

public class CalculoIMC {
    private int calculoId;
    private int usuarioId;
    private Date fecha;
    private float imc;
    private String estadoNutricional;

    // Constructor vacío
    public CalculoIMC() {
    }

    // Constructor con parámetros
    public CalculoIMC(int calculoId, int usuarioId, Date fecha, float imc, String estadoNutricional) {
        this.calculoId = calculoId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.imc = imc;
        this.estadoNutricional = estadoNutricional;
    }

    // Getters y setters para cada propiedad
    public int getCalculoId() {
        return calculoId;
    }

    public void setCalculoId(int calculoId) {
        this.calculoId = calculoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public String getEstadoNutricional() {
        return estadoNutricional;
    }

    public void setEstadoNutricional(String estadoNutricional) {
        this.estadoNutricional = estadoNutricional;
    }

}