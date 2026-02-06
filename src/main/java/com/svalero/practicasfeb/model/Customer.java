package com.svalero.practicasfeb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    // Atributos
    private String nombre; // Obligatorio
    private String dni; // Obligatorio
    private int edad;
    private boolean es_premium;
    private LocalDate fecha_alta;

    // Constructor
    public Customer (String nombre, String dni, int edad, boolean es_premium, LocalDate fecha_alta) {

        // Validación de atributos obligatorios
        if (nombre == null || nombre.isEmpty()) throw new IllegalArgumentException("El nombre es obligatorio");
        if (dni == null || dni.isEmpty()) throw new IllegalArgumentException("El DNI es obligatorio");

        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.es_premium = es_premium;
        this.fecha_alta = fecha_alta;

    }

    // Getters y Setters
    public LocalDate getFechaAlta() {
        return fecha_alta;
    }

    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public boolean isEs_premium() {
        return es_premium;
    }

    public void setEs_premium(boolean es_premium) {
        this.es_premium = es_premium;
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
