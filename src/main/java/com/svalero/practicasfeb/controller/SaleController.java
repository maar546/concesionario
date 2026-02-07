package com.svalero.practicasfeb.controller;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.model.Sale;
import com.svalero.practicasfeb.repository.CarDAO;
import com.svalero.practicasfeb.repository.SaleDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.List;

public class SaleController {

    @FXML private TextField tfNumeroFactura, tfPrecioFinal, tfGarantia;
    @FXML private CheckBox cbEsFinanciado;
    @FXML private DatePicker dpFechaVenta;
    @FXML private Label lbAvisoNumeroFactura, lbAvisoDni, lbAvisoSale, lbContadorSale, lbAvisoPrecioFinal;
    @FXML private Button btnRegistrarSale, btnModificarSale, btnEliminarSale, btnAnteriorSale, btnSiguienteSale;

    @FXML
    public void registrarSale(ActionEvent event) {
        String codigofactura = tfNumeroFactura.getText();
        LocalDate fechaVenta = dpFechaVenta.getValue();
        String precioFinalStr = tfPrecioFinal.getText();
        String garantiaStr = tfGarantia.getText();
        Boolean esFinanciado = cbEsFinanciado.isSelected();

        // Validación de campos obligatorios
        boolean error = false;
        if (codigofactura.isEmpty()) {lbAvisoNumeroFactura.setText("Error: Bill number is mandatory");error = true;}
        if (precioFinalStr.isEmpty()) { lbAvisoPrecioFinal.setText("Error: Final Price is mandatory"); error = true; }


        if (error) return;

            try {

                int garantia;
                garantia = Integer.parseInt(garantiaStr);
                float precioFinal = Float.parseFloat(precioFinalStr);

                Sale newSale = new Sale(codigofactura, fechaVenta, precioFinal, garantia, esFinanciado);
                saleDAO.save(newSale);
                lbAvisoSale.setText("Sale registration completed");
                btnRegistrarSale.setDisable(true);
                cargarDatosSale();

            } catch (NumberFormatException e) {
                lbAvisoPrecioFinal.setText("Error: Final Price need to be a number");
            } catch (Exception e) {
                // ESTO ES LO QUE TE FALTA PARA QUE COMPILE
                // Aquí capturamos el "throws Exception" que declaraste en CarDAO
                e.printStackTrace();
                lbAvisoSale.setText("Error: Database operation failed");
            }

    }

    @FXML
    public void modificarSale(ActionEvent event) {
        String codigofactura = tfNumeroFactura.getText();
        LocalDate fechaVenta = dpFechaVenta.getValue();
        String precioFinalStr = tfPrecioFinal.getText();
        String garantiaStr = tfGarantia.getText();
        Boolean esFinanciado = cbEsFinanciado.isSelected();

        // Validación de campos obligatorios
        boolean error = false;
        if (codigofactura.isEmpty()) {lbAvisoNumeroFactura.setText("Error:No bill number selected");error = true;}
        if (precioFinalStr.isEmpty()) { lbAvisoPrecioFinal.setText("Error: Final Price is mandatory"); error = true; }

        if (error) return;

        try {
            int garantia;
            garantia = Integer.parseInt(garantiaStr);

            float precioFinal = Float.parseFloat(precioFinalStr);

            if (precioFinal <0) {
                lbAvisoPrecioFinal.setText("Error: Price cannot be negativa");
                return;
            }
            Sale saleModificado = new Sale(codigofactura, fechaVenta, precioFinal, garantia, esFinanciado);
            saleDAO.update(saleModificado);

            lbAvisoSale.setText("Sale updated");

            int indiceGuardado = indiceActual;
            cargarDatosSale();
            indiceActual = indiceGuardado;
            mostrarSale(indiceActual);
        } catch (NumberFormatException e) {
            lbAvisoPrecioFinal.setText("Error: Invalid price");
        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoSale.setText("Error updating database");
        }

    }

    @FXML
    public void eliminarSale(ActionEvent event) {
        String codigofactura = tfNumeroFactura.getText();

        try {
            saleDAO.delete(codigofactura);
            lbAvisoSale.setText("Sale deleted");

            cargarDatosSale();

            if (listaSale.isEmpty()) {
                limpiarCamposSale();
                lbContadorSale.setText("0/0");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Borrar todos los campos
    @FXML
    public void limpiarCamposSale() {
            tfNumeroFactura.clear();
            tfNumeroFactura.setEditable(true);

            tfPrecioFinal.clear();
            tfGarantia.clear();
            cbEsFinanciado.setSelected(false);
            dpFechaVenta.setValue(null);

            btnRegistrarSale.setDisable(false);
            btnModificarSale.setDisable(true);
            btnEliminarSale.setDisable(true);
    }


    // MAPEO GENERAL
        private List<Sale> listaSale;
        private int indiceActual = 0;
        private SaleDAO saleDAO = new SaleDAO();

    @FXML
    public void initialize() {
            // Limpieza de avisos
            lbAvisoNumeroFactura.setText("");
            lbAvisoPrecioFinal.setText("");
            cargarDatosSale();
    }

    private void mostrarSale(int indice) {
        Sale sale = listaSale.get(indice);

        tfNumeroFactura.setText(sale.getCodigoFactura());
        tfNumeroFactura.setEditable(false); // para no poder modificar la factura

        tfPrecioFinal.setText(String.valueOf(sale.getPrecioFinal()));
        tfGarantia.setText(String.valueOf(sale.getGarantia()));
        cbEsFinanciado.setSelected(sale.isFinanciado());
        dpFechaVenta.setValue(sale.getFechaVenta());

        // ACTUALIZAR CONTADOR
        if (lbContadorSale != null) {
            lbContadorSale.setText((indice + 1) + " / " + listaSale.size());
        }

    }

    // MOSTRAR DATOS EN LA PANTALLA AL ENTRAR EN ENTITY/CUSTOMER
    private void cargarDatosSale() {
        btnModificarSale.setDisable(false);
        btnEliminarSale.setDisable(false);
        btnRegistrarSale.setDisable(true);

        try {
            // Llamada a findAll() del DAO
            listaSale = saleDAO.findAll();

            if (listaSale != null && !listaSale.isEmpty()) {
                indiceActual = 0;
                mostrarSale(indiceActual);
            } else {
                lbAvisoSale.setText("Database is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
            lbAvisoSale.setText("Error loading data");
        }
    }

    @FXML
    private void siguienteSale(ActionEvent event) {
        if (listaSale != null && indiceActual < listaSale.size() - 1) {
            indiceActual++;
            mostrarSale(indiceActual);
        }
    }

    @FXML
    private void anteriorSale(ActionEvent event) {
        if (listaSale != null && indiceActual > 0) {
            indiceActual--;
            mostrarSale(indiceActual);
        }
    }
}
