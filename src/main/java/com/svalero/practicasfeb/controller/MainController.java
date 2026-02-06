package com.svalero.practicasfeb.controller;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane areaContenido;

    @FXML
    public void verCarView(ActionEvent event) {
        try {

            // Cargamos el archivo FXML.
            Parent view = FXMLLoader.load(getClass().getResource("/com/svalero/practicasfeb/car_view.fxml"));

            // 2. Limpiamos lo que haya en el AnchorPane y añadimos la nueva vista
            areaContenido.getChildren().setAll(view);

        } catch (IOException e) {
            System.err.println("Error al cargar Car_View: " + e.getMessage());
        }
    }

    @FXML
    public void verCustomerView(ActionEvent event) {
        try {

            // Cargamos el archivo FXML.
            Parent view = FXMLLoader.load(getClass().getResource("/com/svalero/practicasfeb/customer_view.fxml"));

            // 2. Limpiamos lo que haya en el AnchorPane y añadimos la nueva vista
            areaContenido.getChildren().setAll(view);

        } catch (IOException e) {
            System.err.println("Error al cargar Customer_View: " + e.getMessage());
        }
    }

    @FXML
    public void verSellView(ActionEvent event) {
        try {

            // Cargamos el archivo FXML.
            Parent view = FXMLLoader.load(getClass().getResource("/com/svalero/practicasfeb/sell_view.fxml"));

            // 2. Limpiamos lo que haya en el AnchorPane y añadimos la nueva vista
            areaContenido.getChildren().setAll(view);

        } catch (IOException e) {
            System.err.println("Error al cargar Sell_View: " + e.getMessage());
        }
    }
}