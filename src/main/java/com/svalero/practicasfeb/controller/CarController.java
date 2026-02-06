package com.svalero.practicasfeb.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CarController {

    @FXML private TextField tfMatricula, tfMarca, tfModelo, tfPrecioBase, tfUltimaRevision;
    @FXML private Label lbAvisoMatricula, lbAvisoMarca;
    @FXML private Button btnRegistrarCoche, btnModificarCoche, btnEliminarCoche;

    @FXML
    public void registrarCoche(ActionEvent event) {
        // TODO CAMBIAR Y ADAPTAR PARA NUESTRO TRABAJO
        String matricula = tfTitulo.getText();
        String marca = tfPrecio.getText();
        String modelo = tfGenero.getText();
        float precioBase = dpFecha.getValue();
        LocalDate digital = cbDigital.isSelected();

        // Validación de campos obligatorios
        if (titulo.isEmpty() || precioStr.isEmpty()) {
            lbStatusV.setText("Error: Título y Precio son obligatorios");
            return;
        }

        try {
            float precio = Float.parseFloat(precioStr);
            Videojuego nuevoV = new Videojuego(titulo, precio, genero, fecha, digital);

            // Al estar envuelta, se actualiza la tabla (UI) y el repositorio a la vez.
            listaVideojuegos.add(nuevoV);

            lbStatusV.setText("Videojuego registrado correctamente");
            limpiarCamposVideojuego();
        } catch (NumberFormatException e) {
            lbStatusV.setText("Error: El precio debe ser un número");
        }
    }

    @FXML
    public void modificarCoche(ActionEvent event) {
        // TODO HACER EL METODO
    }

    @FXML
    public void eliminarCoche(ActionEvent event) {
        // TODO HACER EL METODO
    }

    @FXML
    public void initialize() {
        // Limpiamos todo
        lbAvisoMatricula.setText("");
        lbAvisoMarca.setText("");
    }
}
