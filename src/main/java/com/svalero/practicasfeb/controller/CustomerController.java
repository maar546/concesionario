package com.svalero.practicasfeb.controller;

import com.svalero.practicasfeb.model.Customer;
import com.svalero.practicasfeb.repository.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.List;

public class CustomerController {

    @FXML private TextField tfNombre, tfDni, tfEdad;
    @FXML private CheckBox cbEsPremium;
    @FXML private DatePicker dpFechaAlta;
    @FXML private Label lbAvisoNombre, lbAvisoDni, lbAvisoCustomer, lbContadorCustomer;
    @FXML private Button btnRegistrarCustomer, btnModificarCustomer, btnEliminarCustomer, btnAnteriorCustomer, btnSiguienteCustomer;

    @FXML
    public void registrarCustomer(ActionEvent event) {

        String nombre = tfNombre.getText();
        String dni = tfDni.getText();
        String edadStr = tfEdad.getText();
        Boolean esPremium = cbEsPremium.isSelected();
        LocalDate fechaAlta = dpFechaAlta.getValue();

        // Validación de campos obligatorios
        boolean error = false;
        if (nombre.isEmpty()) {lbAvisoNombre.setText("Error: Name is mandatory");error = true;}
        if (dni.isEmpty()) {lbAvisoDni.setText("Error: DNI is mandatory");error = true;}

        if (error) return;

        try {

            int edad;
            edad = Integer.parseInt(edadStr);

                Customer newCustomer = new Customer(nombre, dni, edad, esPremium, fechaAlta);
                customerDAO.save(newCustomer);
                lbAvisoCustomer.setText("Customer registration completed");
                btnRegistrarCustomer.setDisable(true);
                cargarDatosCustomer();
            } catch (Exception e) {
            // ESTO ES LO QUE TE FALTA PARA QUE COMPILE
            // Aquí capturamos el "throws Exception" que declaraste en CarDAO
            e.printStackTrace();
            lbAvisoCustomer.setText("Error: Database operation failed");
        }
    }

    @FXML
    public void modificarCustomer(ActionEvent event) {

        String nombre = tfNombre.getText();
        String dni = tfDni.getText();
        String edadStr = tfEdad.getText();
        Boolean es_premium = cbEsPremium.isSelected();
        LocalDate fecha_alta = dpFechaAlta.getValue();

        // Validación de campos obligatorios
        boolean error = false;
        if (nombre.isEmpty()) { lbAvisoNombre.setText("Error: No name selected to modify"); error = true; }
        if (dni.isEmpty()) { lbAvisoDni.setText("Error: DNI is mandatory"); error = true; }

        if (error) return;

        try {
            int edad;
            edad = Integer.parseInt(edadStr);

            if (nombre == null ) {
                lbAvisoNombre.setText("Error: Name can not be empty");
                return;
            }

            Customer customerModificado = new Customer(nombre, dni, edad, es_premium, fecha_alta);
            customerDAO.update(customerModificado);

            lbAvisoCustomer.setText("Customer updated");

            int indiceGuardado = indiceActual;
            cargarDatosCustomer();
            indiceActual = indiceGuardado;
            mostrarCustomer(indiceActual);
        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoCustomer.setText("Error updating database");
        }
    }


    @FXML
    public void eliminarCustomer(ActionEvent event) {

        String dni = tfDni.getText().trim();

        try {
            customerDAO.delete(dni);
            lbAvisoCustomer.setText("Customer deleted");

            cargarDatosCustomer();

            if (listaCustomer.isEmpty()) {
                limpiarCamposCustomer();
                lbContadorCustomer.setText("0/0");
            }

        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoCustomer.setText("Error: Couldn't delete");
        }
    }

    // Borrar todos los campos
    @FXML
    public void limpiarCamposCustomer() {
        tfNombre.clear();
        tfNombre.setEditable(true);

        tfDni.clear();
        tfEdad.clear();
        cbEsPremium.setSelected(false);
        dpFechaAlta.setValue(null);

        btnRegistrarCustomer.setDisable(false);
        btnModificarCustomer.setDisable(true);
        btnEliminarCustomer.setDisable(true);
    }


    // MAPEO GENERAL
    private List<Customer> listaCustomer;
    private int indiceActual = 0;
    private CustomerDAO customerDAO = new CustomerDAO();


    @FXML
    public void initialize() {
        // Limpieza de avisos
        lbAvisoNombre.setText("");
        lbAvisoDni.setText("");
        cargarDatosCustomer();
    }

    private void mostrarCustomer(int indice) {
        Customer customer = listaCustomer.get(indice);

        tfNombre.setText(customer.getNombre());

        tfDni.setText(customer.getDni());
        //tfDni.setEditable(false); // para no poder modificar

        tfEdad.setText(String.valueOf(customer.getEdad()));
        cbEsPremium.setSelected(customer.isPremium());
        dpFechaAlta.setValue(customer.getFechaAlta());

        // ACTUALIZAR CONTADOR
        if (lbContadorCustomer != null) {
            lbContadorCustomer.setText((indice + 1) + " / " + listaCustomer.size());
        }
    }

    // MOSTRAR DATOS EN LA PANTALLA AL ENTRAR EN ENTITY/CUSTOMER
    private void cargarDatosCustomer() {
        btnModificarCustomer.setDisable(false);
        btnEliminarCustomer.setDisable(false);
        btnRegistrarCustomer.setDisable(true);

        try {
            // Llamada a findAll() del DAO
            listaCustomer = customerDAO.findAll();

            if (listaCustomer != null && !listaCustomer.isEmpty()) {
                indiceActual = 0;
                mostrarCustomer(indiceActual);
            } else {
                lbAvisoCustomer.setText("Database is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoCustomer.setText("Error loading data");
        }
    }

    @FXML
    private void siguienteCustomer(ActionEvent event) {
        if (listaCustomer != null && indiceActual < listaCustomer.size() - 1) {
            indiceActual++;
            mostrarCustomer(indiceActual);
        }
    }

    @FXML
    private void anteriorCustomer(ActionEvent event) {
        if (listaCustomer != null && indiceActual > 0) {
            indiceActual--;
            mostrarCustomer(indiceActual);
        }
    }
}

