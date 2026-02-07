package com.svalero.practicasfeb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    // Atributos
    private String nombre; // Obligatorio
    private String dni; // Obligatorio
    private int edad;
    private boolean esPremium;
    private LocalDate fechaAlta;

    // Constructor
    public Customer (String nombre, String dni, int edad, boolean esPremium, LocalDate fechaAlta) {

        // Validación de atributos obligatorios
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("Name is mandatory");
        if (dni == null || dni.isEmpty()) throw new IllegalArgumentException("ID Number is mandatory");

        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.esPremium = esPremium;
        this.fechaAlta = fechaAlta;
    }

    // Getters y Setters
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isPremium() {
        return esPremium;
    }
    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
