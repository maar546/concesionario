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
import java.util.List;

public class CarController {

    @FXML private TextField tfMatricula, tfMarca, tfModelo, tfPrecioBase;
    @FXML private DatePicker dpUltimaRevision;
    @FXML private Label lbAvisoMatricula, lbAvisoMarca, lbAvisoPrecio, lbAvisoCoche, lbContadorCoche;
    @FXML private Button btnRegistrarCoche, btnModificarCoche, btnEliminarCoche, btnAnteriorCoche, btnSiguienteCoche;

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
        if (precioBaseStr.isEmpty()) { lbAvisoPrecio.setText("Error: Starting Price is mandatory"); error = true; }

        if (error) return;

        try {
            float precioBase = Float.parseFloat(precioBaseStr);

            Car newCar = new Car(matricula, marca, modelo, precioBase, ultima_revision);
            carDAO.save(newCar);

            lbAvisoCoche.setText("Car registration completed");

            btnRegistrarCoche.setDisable(true);
            cargarDatosCoche();

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

        String matricula = tfMatricula.getText();
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String precioBaseStr = tfPrecioBase.getText();
        LocalDate ultima_revision = dpUltimaRevision.getValue();

        boolean error = false;
        if (matricula.isEmpty()) { lbAvisoCoche.setText("Error: No car selected to modify"); error = true; }
        if (precioBaseStr.isEmpty()) { lbAvisoPrecio.setText("Error: Starting Price is mandatory"); error = true; }

        if (error) return;

        try {
            float precioBase = Float.parseFloat(precioBaseStr);

            if (precioBase <0) {
                lbAvisoPrecio.setText("Error: Price cannot be negativa");
                return;
            }

            Car cocheModificado = new Car(matricula, marca, modelo, precioBase, ultima_revision);
            carDAO.update(cocheModificado);

            lbAvisoCoche.setText("Car updated");

            int indiceGuardado = indiceActual;
            cargarDatosCoche();
            indiceActual = indiceGuardado;
            mostrarCoche(indiceActual);
        } catch (NumberFormatException e) {
            lbAvisoPrecio.setText("Error: Invalid price");
        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoCoche.setText("Error updating database");
        }
    }

    @FXML
    public void eliminarCoche(ActionEvent event) {

        String matricula = tfMatricula.getText();

        try {
            carDAO.delete(matricula);
            lbAvisoCoche.setText("Car deleted");

            cargarDatosCoche();

            if (listaCoches.isEmpty()) {
                limpiarCamposCoche();
                lbContadorCoche.setText("0/0");
            }

        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoCoche.setText("Error: Couldn't delete");
        }

    }

    // Borrar todos los campos
    @FXML
    public void limpiarCamposCoche() {
        tfMatricula.clear();
        tfMatricula.setEditable(true); // quita el bloqueo de escribir el matrícula

        tfMarca.clear();
        tfModelo.clear();
        tfPrecioBase.clear();
        dpUltimaRevision.setValue(null);

        btnRegistrarCoche.setDisable(false);
        btnModificarCoche.setDisable(true);
        btnEliminarCoche.setDisable(true);
    }

    // MAPEO GENERAL
    private List<Car> listaCoches;
    private int indiceActual = 0;
    private CarDAO carDAO = new CarDAO();

    @FXML
    public void initialize() {
        // Limpieza de avisos
        lbAvisoMatricula.setText("");
        lbAvisoMarca.setText("");
        cargarDatosCoche();
    }

    private void mostrarCoche(int indice) {
        Car coche = listaCoches.get(indice);

        tfMatricula.setText(coche.getMatricula());
        tfMatricula.setEditable(false); // para no poder modificar la matrícula

        tfMarca.setText(coche.getMarca());
        tfModelo.setText(coche.getModelo());
        tfPrecioBase.setText(String.valueOf(coche.getPrecioBase()));
        dpUltimaRevision.setValue(coche.getUltimaRevision());

        // ACTUALIZAR CONTADOR
        if (lbContadorCoche != null) {
            lbContadorCoche.setText((indice + 1) + " / " + listaCoches.size());
        }
    }

    // MOSTRAR DATOS EN PANTALLA AL ENTRAR EN ENTITY/CAR
    private void cargarDatosCoche() {

        btnModificarCoche.setDisable(false);
        btnEliminarCoche.setDisable(false);
        btnRegistrarCoche.setDisable(true);

        try {
            // Llamada a findAll() del DAO
            listaCoches = carDAO.findAll();

            if (listaCoches != null && !listaCoches.isEmpty()) {
                indiceActual = 0;
                mostrarCoche(indiceActual);
            } else {
                lbAvisoCoche.setText("Database is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoCoche.setText("Error loading data");
        }
    }

    @FXML
    public void siguienteCoche(ActionEvent event) {
        if (listaCoches != null && indiceActual < listaCoches.size() - 1) {
            indiceActual++;
            mostrarCoche(indiceActual);
        }
    }

    @FXML
    public void anteriorCoche(ActionEvent event) {
        if (listaCoches != null && indiceActual > 0) {
            indiceActual--;
            mostrarCoche(indiceActual);
        }
    }


}
