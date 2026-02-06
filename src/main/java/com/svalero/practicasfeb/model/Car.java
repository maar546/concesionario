package com.svalero.practicasfeb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Car implements Serializable {

    // Atributos
    private String matricula; // Obligatorio
    private String marca; // Obligatorio
    private String modelo;
    private float precioBase;
    private LocalDate ultimaRevision;

    // Constructor
    public Car(String matricula, String marca, String modelo, float precioBase, LocalDate ultimaRevision) {

        // Validación de atributos obligatorios
        if (matricula == null || matricula.isEmpty()) throw new IllegalArgumentException("License Plate is mandatory");
        if (precioBase <= 0) throw new IllegalArgumentException("Starting Price is mandatory and need to be more than 0");

        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.precioBase = precioBase;
        this.ultimaRevision = ultimaRevision;
    }

    // Getters y Setters
    public String getMatricula() {
            return matricula;
        }
    public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

    public String getMarca() {
            return marca;
        }
    public void setMarca(String marca) {
            this.marca = marca;
        }

    public String getModelo() {
            return modelo;
        }
    public void setModelo(String modelo) {
            this.modelo = modelo;
        }

    public float getPrecioBase() {
            return precioBase;
        }
    public void setPrecioBase(float precioBase) {
            this.precioBase = precioBase;
        }

    public LocalDate getUltimaRevision() {
            return ultimaRevision;
        }
    public void setUltimaRevision(LocalDate ultimaRevision) {
            this.ultimaRevision = ultimaRevision;
        }
}


