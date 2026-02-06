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

    public static void save(Car car) throws Exception {

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


            //ps.setDate(5, java.sql.Date.valueOf(car.getUltimaRevision()));

            System.out.println("Intentando guardar coche...");

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
                return new Car(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getFloat("precio_base"),
                        rs.getDate("ultima_revision").toLocalDate()
                );
            }
        }
        return null;
    }

    public List<Car> findAll() throws Exception {

        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM coche";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cars.add(new Car(
                        rs.getString("matricula"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getFloat("precio_base"),
                        rs.getDate("ultima_revision").toLocalDate()
                ));
            }
        }
        return cars;
    }
}