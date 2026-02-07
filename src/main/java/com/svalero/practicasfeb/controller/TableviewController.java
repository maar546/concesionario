package com.svalero.practicasfeb.controller;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.model.Customer;
import com.svalero.practicasfeb.repository.CarDAO;
import com.svalero.practicasfeb.repository.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableviewController {

    // DAOs para acceder a MariaDB
    private final CarDAO carDAO = new CarDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    // private final SaleDAO saleDAO = new SaleDAO();

    // Tabla de Coches
    @FXML private TableView<Car> tvCoches;
    @FXML private TableColumn<Car, String> colMatricula;
    @FXML private TableColumn<Car, String> colMarca;
    @FXML private TableColumn<Car, String> colModelo;
    @FXML private TableColumn<Car, Float> colPrecioBase;
    @FXML private TableColumn<Car, LocalDate> colUltimaRevision;

    // Tabla de Customers
    @FXML private TableView<Customer> tvCustomers;
    @FXML private TableColumn<Customer, String> colDni;
    @FXML private TableColumn<Customer, String> colNombre;
    @FXML private TableColumn<Customer, Integer> colEdad;
    @FXML private TableColumn<Customer, Boolean> colEsPremium;
    @FXML private TableColumn<Customer, LocalDate> colFechaAlta;

    // Tabla de Sales
    // @FXML private TableView<Sale> tvSales;
    // @FXML private TableColumn<Sale, String> colNumeroFactura;
    // @FXML private TableColumn<Sale, LocalDate> colFechaVenta;
    // @FXML private TableColumn<Sale, Float> colPrecioFinal;
    // @FXML private TableColumn<Sale, Integer> colGarantia;
    // @FXML private TableColumn<Sale, Boolean> colEsFinanciado;

    @FXML
    public void initialize() {
        System.out.println("Configurando columnas...");
        configurarColumnas();
        System.out.println("Llamando a BD...");
        cargarDatosDesdeBD();
        System.out.println(">>> CONTROLADOR DE TABLA INICIADO");
    }

    private void configurarColumnas() {

        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colPrecioBase.setCellValueFactory(new PropertyValueFactory<>("precioBase"));
        colUltimaRevision.setCellValueFactory(new PropertyValueFactory<>("ultimaRevision"));

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEsPremium.setCellValueFactory(new PropertyValueFactory<>("esPremium"));
        colFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));

        // colNumeroFactura.setCellValueFactory(new PropertyValueFactory<>("codigoFactura"));
        // colFechaVenta.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
        // colPrecioFinal.setCellValueFactory(new PropertyValueFactory<>("precioFinal"));
        // colGarantia.setCellValueFactory(new PropertyValueFactory<>("garantia"));
        // colEsFinanciado.setCellValueFactory(new PropertyValueFactory<>("esFinanciado"));
    }

    private void cargarDatosDesdeBD() {
        try {
            // Persistencia transparente: carga automática al abrir la vista
            tvCoches.setItems(FXCollections.observableArrayList(carDAO.findAll()));
            tvCustomers.setItems(FXCollections.observableArrayList(customerDAO.findAll()));
            // tvSales.setItems(FXCollections.observableArrayList(saleDAO.findAll()));

            tvCoches.refresh(); // Forzar dibujado
        } catch (Exception e) {
            System.err.println("Error al cargar datos de MariaDB: " + e.getMessage());
        }
    }
}
