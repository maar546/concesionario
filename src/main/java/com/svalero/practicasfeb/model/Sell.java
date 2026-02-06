package com.svalero.practicasfeb.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Sell implements Serializable {

    // Atributos
    private String codigo_factura; // Obligatorio
    private LocalDate fecha_venta;
    private float precio_final; // Obligatoriono
    private int garantia;
    private boolean es_financiado;

    // Constructor
    public Sell (String codigo_factura, LocalDate fecha_venta, float precio_final, int garantia, boolean es_financiado) {

        // Validación de atributos obligatorios
        if (codigo_factura == null || codigo_factura.isEmpty()) throw new IllegalArgumentException("El codigo de factura es obligatorio");
        if (precio_final = 0) { throw new IllegalArgumentException("El precio final debe ser mayor que 0");

        this.codigo_factura = codigo_factura;
        this.fecha_venta= fecha_venta;
        this.precio_final = precio_final;
        this.garantia = garantia;
        this.es_financiado = es_financiado;
    }

   // Getters y Setters
    public String getCodigo_factura() {return codigo_factura;}
    public void setCodigo_factura(String codigo_factura) {this.codigo_factura = codigo_factura;}
    public LocalDate getFecha_venta() {return fecha_venta;}
    public void setFecha_venta(LocalDate fecha_venta) {this.fecha_venta = fecha_venta;}
    public float getPrecio_final() {return precio_final;}
    public void setPrecio_final(float precio_final) {this.precio_final = precio_final;}
    public int getGarantia() {return garantia;}
    public void setGarantia(int garantia) {this.garantia = garantia;}
    public boolean isEs_financiado() {return es_financiado;}
    public void setEs_financiado(boolean es_financiado) {this.es_financiado = es_financiado;}
}