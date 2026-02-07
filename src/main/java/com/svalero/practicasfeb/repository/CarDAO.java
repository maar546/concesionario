package com.svalero.practicasfeb.repository;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;



public class CarDAO {

    public static List<Car> cars = new ArrayList<>();

    public void save(Car car) throws Exception {

        String sql = """
            INSERT INTO coche (matricula, marca, modelo, precio_base, ultima_revision)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, car.getMatricula());
            ps.setString(2, car.getMarca());
            ps.setString(3, car.getModelo());
            ps.setFloat(4, car.getPrecioBase());

            if (car.getUltimaRevision() != null) {
                ps.setDate(5, java.sql.Date.valueOf(car.getUltimaRevision()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            System.out.println("Intentando guardar coche...");

            ps.executeUpdate();
        }
    }

    public void update(Car car) throws Exception {

        String sql = """
                UPDATE coche
                SET marca = ?, modelo = ?, precio_base = ?, ultima_revision = ?
                WHERE matricula = ?
        """;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, car.getMarca());
            ps.setString(2, car.getModelo());
            ps.setFloat(3, car.getPrecioBase());

            if (car.getUltimaRevision() != null) {
                ps.setDate(4, java.sql.Date.valueOf(car.getUltimaRevision()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            ps.setString(5, car.getMatricula());

            System.out.println("Intentando modificar coche...");

            ps.executeUpdate();
        }
    }

    public void delete(String matricula) throws Exception {

        String sql = "DELETE FROM coche WHERE matricula = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ps.executeUpdate();
        }
    }

    public Car findByMatricula(String matricula) throws Exception {

        String sql = "SELECT * FROM coche WHERE matricula = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, matricula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Evitar que dé error por fecha null
                java.sql.Date sqlDate = rs.getDate("ultima_revision");
                java.time.LocalDate fecha = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                return new Car(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getFloat("precio_base"),
                        fecha
                );
            }
        }
        return null;
    }

    public static List<Car> findAll() throws Exception {

        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM coche";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                // Evitar que dé error por fecha null
                java.sql.Date sqlDate = rs.getDate("ultima_revision");
                java.time.LocalDate fecha = (sqlDate != null) ? sqlDate.toLocalDate() : null;

                cars.add(new Car(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getFloat("precio_base"),
                        fecha
                ));
            }
        }
        return cars;
    }
}