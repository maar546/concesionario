package com.svalero.practicasfeb.controller;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.repository.CarDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CarController {

    @FXML private TextField tfMatricula, tfMarca, tfModelo, tfPrecioBase;
    @FXML private DatePicker dpUltimaRevision;
    @FXML private Label lbAvisoMatricula, lbAvisoMarca, lbAvisoPrecio, lbAvisoCoche;
    @FXML private Button btnRegistrarCoche, btnModificarCoche, btnEliminarCoche;

    @FXML
    public void registrarCoche(ActionEvent event) {

        String matricula = tfMatricula.getText();
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String precioBaseStr = tfPrecioBase.getText();
        LocalDate ultima_revision = dpUltimaRevision.getValue();

        // Validación de campos obligatorios
        boolean error = false;
        if (matricula.isEmpty()) { lbAvisoMatricula.setText("Error: License plate is mandatory"); error = true; }
        if (marca.isEmpty()) { lbAvisoMarca.setText("Error: Mark is mandatory"); error = true; }
        if (precioBaseStr.isEmpty()) { lbAvisoPrecio.setText("Error: Starting Price is mandatory"); error = true; }

        if (error) return;

        try {
            float precioBase = Float.parseFloat(precioBaseStr);

            // USAR EL CONSTRUCTOR CON PARÁMETROS (La clave de la verdad)
            Car newCar = new Car(matricula, marca, modelo, precioBase, ultima_revision);

            // INSTANCIAR EL DAO (No es estático)
            CarDAO carDAO = new CarDAO();
            carDAO.save(newCar);

            lbAvisoCoche.setText("Car registration completed");
            //limpiarCamposCoche();

        } catch (NumberFormatException e) {
            lbAvisoPrecio.setText("Error: Starting Price need to be a number");
        } catch (Exception e) {
            // ESTO ES LO QUE TE FALTA PARA QUE COMPILE
            // Aquí capturamos el "throws Exception" que declaraste en CarDAO
            e.printStackTrace();
            lbAvisoCoche.setText("Error: Database operation failed");
        }
    }

    @FXML
    public void modificarCoche(ActionEvent event) {

        // TODO Obtener datos a modificar


        // TODO Validar que ha seleccionado algo

    }

    @FXML
    public void eliminarCoche(ActionEvent event) {
        // TODO HACER EL METODO
        // TODO Obtener datos a eliminar

        // TODO Verificación de selección

        // TODO Borrar de la BBDD

        lbAvisoCoche.setText("Car deleted");
        limpiarCamposCoche();
    }

    // Borrar todos los campos
    @FXML
    public void limpiarCamposCoche() {
        tfMatricula.clear();
        tfMarca.clear();
        tfModelo.clear();
        tfPrecioBase.clear();
        dpUltimaRevision.setValue(null);

        btnRegistrarCoche.setDisable(false);
        btnModificarCoche.setDisable(true);
        btnEliminarCoche.setDisable(true);
    }

    @FXML
    public void initialize() {
        // Limpieza de avisos
        lbAvisoMatricula.setText("");
        lbAvisoMarca.setText("");
    }
}
