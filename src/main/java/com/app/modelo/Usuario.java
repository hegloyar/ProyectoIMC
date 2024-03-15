/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.modelo;

import java.util.Date;

/**
 *
 * @author galva
 */


//Descripción: Representa a un usuario de la aplicación.
//Métodos principales: getUsuarioId(), setUsuarioId(), getNombreCompleto(), setNombreCompleto()

public class Usuario {
    private int usuarioId;
    private String nombreCompleto;
    private String nombreUsuario;
    private int edad;
    private char sexo;
    private float estatura;
    private float peso;
    private String password;
    private Date fechaRegistro;

    public Usuario() {
        // Constructor vacío  útil para crear instancias sin inicializar los atributos
    }

    // Getters y setters para usuarioId
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    // Getters y setters para nombreCompleto
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    // Getters y setters para nombreUsuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    // Getters y setters para edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Getters y setters para sexo
    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    // Getters y setters para estatura
    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    // Getters y setters para peso
    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    // Getters y setters para password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    // Getters y setters para fechaRegistro
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
