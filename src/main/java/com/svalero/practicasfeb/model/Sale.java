package com.svalero.practicasfeb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable {

    // Atributos
    private String codigoFactura; // Obligatorio
    private LocalDate fechaVenta;
    private float precioFinal; // Obligatoriono
    private int garantia;
    private boolean financiado;

    // Constructor
    public Sale(String codigoFactura, LocalDate fechaVenta, float precioFinal, int garantia, boolean financiado) {

        // Validación de atributos obligatorios
        if (codigoFactura == null || codigoFactura.isEmpty()) throw new IllegalArgumentException("Bill number is mandatory");
        if (precioFinal <= 0) { throw new IllegalArgumentException("Final price is mandatory and need to be more than 0");}

        this.codigoFactura = codigoFactura;
        this.fechaVenta = fechaVenta;
        this.precioFinal = precioFinal;
        this.garantia = garantia;
        this.financiado = financiado;
    }

   // Getters y Setters
    public String getCodigoFactura() {return codigoFactura;}
    public void setCodigoFactura(String codigoFactura) {this.codigoFactura = codigoFactura;}

    public LocalDate getFechaVenta() {return fechaVenta;}
    public void setFechaVenta(LocalDate fechaVenta) {this.fechaVenta = fechaVenta;}

    public float getPrecioFinal() {return precioFinal;}
    public void setPrecioFinal(float precioFinal) {this.precioFinal = precioFinal;}

    public int getGarantia() {return garantia;}
    public void setGarantia(int garantia) {this.garantia = garantia;}

    public boolean isFinanciado() {return financiado;}
    public void setFinanciado(boolean financiado) {this.financiado = financiado;}
}