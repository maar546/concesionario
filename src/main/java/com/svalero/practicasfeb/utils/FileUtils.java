package com.svalero.practicasfeb.utils;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.model.DataStore;
import com.svalero.practicasfeb.repository.CarDAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String FILE_NAME = "datos_concesionario.dat";

    // Guarda las 3 listas en 1 archivo
    public static void guardarTodo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            DataStore mochila = new DataStore();
            mochila.cars = CarDAO.cars;
            // TODO añadir las mochilas de CUSTOMER y lista SALE para sincronizar con BBDD

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
            // TODO añadir las mochilas de CUSTOMER y lista SALE para sincronizar con la BBDD

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
