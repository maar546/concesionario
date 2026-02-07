package com.svalero.practicasfeb;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.repository.CarDAO;
import com.svalero.practicasfeb.utils.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void init() throws Exception {
        FileUtils.cargarTodo();

        // COMPROBAR SI MariaDB TIENE DATOS
        CarDAO carDAO = new CarDAO();
        try {
            if (carDAO.findAll().isEmpty() && !CarDAO.cars.isEmpty()) {

                for (Car coche : CarDAO.cars) {
                    carDAO.save(coche);
                }
                System.out.println("BBDD restored");
            }
        } catch (Exception e) {
            System.err.println(">> Couldn't connect with DB, but .dat is loaded");
        }

        // TODO añadir comprobaciones de CUSTOMER y SALE
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Car Dealer - SValero");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        CarDAO dao = new CarDAO();
        CarDAO.cars = dao.findAll(); // Para pasar los datos de la BBDD a la lista .dat

        FileUtils.guardarTodo();
    }
}

