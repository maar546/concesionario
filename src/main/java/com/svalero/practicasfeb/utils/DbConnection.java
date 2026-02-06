package com.svalero.practicasfeb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:mariadb://localhost:3306/concesionario";
    private static final String user = "root";
    private static final String password = "1234";

    /**
     * Devuelve una conexión activa a la base de datos MariaDB
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, user, password);
    }
}