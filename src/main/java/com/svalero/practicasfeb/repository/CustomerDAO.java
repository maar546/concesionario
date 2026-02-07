package com.svalero.practicasfeb.repository;

import com.svalero.practicasfeb.model.Customer;
import com.svalero.practicasfeb.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {

    public static List<Customer> customers = new ArrayList<>();

    public void save(Customer customer) throws Exception {

        String sql = """
            INSERT INTO cliente (dni, nombre, edad, es_premium, fecha_alta)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getDni());
            ps.setString(2, customer.getNombre());
            ps.setInt(3, customer.getEdad());
            ps.setBoolean(4, customer.isPremium());

            if (customer.getFechaAlta() != null) {
                ps.setDate(5, java.sql.Date.valueOf(customer.getFechaAlta()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            System.out.println("Intentando guardar cliente...");

            ps.executeUpdate();
        }
    }

    public void update(Customer customer) throws Exception {

        String sql = """
                UPDATE cliente
                SET nombre = ?, edad = ?, es_premium = ?, fecha_alta = ?
                WHERE dni = ?
        """;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getNombre());
            ps.setInt(2, customer.getEdad());
            ps.setBoolean(3, customer.isPremium());

            if (customer.getFechaAlta() != null) {
                ps.setDate(4, java.sql.Date.valueOf(customer.getFechaAlta()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setString(5, customer.getDni());

            System.out.println("Intentando modificar cliente...");

            ps.executeUpdate();
        }
    }

    public void delete(String dni) throws Exception {

        String sql = "DELETE FROM cliente WHERE dni = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ps.executeUpdate();
        }
    }

    public Customer findbyDni(String dni) throws Exception {

        String sql = "SELECT * FROM cliente WHERE dni = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Evitar que dé error por fecha null
                java.sql.Date sqlDate = rs.getDate("fechaAlta");
                java.time.LocalDate fecha = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                return new Customer(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getBoolean("esPremium"),
                        fecha
                );
            }
        }
        return null;
    }

    public static List<Customer> findAll() throws Exception {

        List<Customer> customer = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                // Evitar que dé error por fecha null
                java.sql.Date sqlDate = rs.getDate("fecha_alta");
                java.time.LocalDate fecha = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                customer.add(new Customer(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getInt("edad"),
                        rs.getBoolean("es_premium"),
                        fecha
                ));
            }
        }
        return customer;
    }
}