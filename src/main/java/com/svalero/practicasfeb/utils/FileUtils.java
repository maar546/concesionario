package com.svalero.practicasfeb.utils;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.model.Customer;
import com.svalero.practicasfeb.model.DataStore;
import com.svalero.practicasfeb.repository.CarDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.svalero.practicasfeb.repository.CustomerDAO;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    private static final String FILE_NAME = "datos_concesionario.dat";

    // Configuramos el Mapper UNA SOLA VEZ con soporte para Java 8 (LocalDate)
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .enable(SerializationFeature.INDENT_OUTPUT);

    // Guarda las 3 listas en 1 archivo
    public static void guardarTodo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            DataStore mochila = new DataStore();
            mochila.cars = CarDAO.cars;
            mochila.customers = CustomerDAO.customers;
        // TODO añadir las mochilas lista SALE para sincronizar con BBDD

            oos.writeObject(mochila);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Carga las 3 listas en orden
    public static void cargarTodo() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            DataStore mochila = (DataStore) ois.readObject();
            CarDAO.cars = mochila.cars;
            CustomerDAO.customers = mochila.customers;
            // TODO añadir las mochilas lista SALE para sincronizar con la BBDD

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static <T> void exportToJson(List<T> data, String fileName) {
        try {
            mapper.writeValue(new File(fileName), data);
            System.out.println("Datos exportados correctamente a " + fileName);
        } catch (IOException e) {
            System.err.println("Error al exportar datos: " + e.getMessage());
        }
    }
}

