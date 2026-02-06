package com.svalero.practicasfeb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Car implements Serializable {

    // Atributos
    private String matricula; // Obligatorio
    private String marca; // Obligatorio
    private String modelo;
    private float precio_base;
    private LocalDate ultima_revision;

    // Constructor
    public Car(String matricula, String marca, String modelo, float precio_base, LocalDate ultima_revision) {

        // Validación de atributos obligatorios
        if (matricula == null || matricula.isEmpty()) throw new IllegalArgumentException("La matrícula es obligatoria");
        if (marca == null || marca.isEmpty()) throw new IllegalArgumentException("La marca es obligatoria");

        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.precio_base = precio_base;
        this.ultima_revision = ultima_revision;
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
            return precio_base;
        }
        public void setPrecioBase(float precio_base) {
            this.precio_base = precio_base;
        }

        public LocalDate getUltimaRevision() {
            return ultima_revision;
        }
        public void setUltimaRevision(LocalDate ultima_revision) {
            this.ultima_revision = ultima_revision;
        }
    }


