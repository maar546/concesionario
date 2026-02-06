package com.svalero.practicasfeb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/concesionario";
        String usuario = "root";
        String password = "";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            System.out.println("Conexión exitosa a MariaDB");
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
        }
    }
}