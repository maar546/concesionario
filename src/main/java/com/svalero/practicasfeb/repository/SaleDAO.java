package com.svalero.practicasfeb.repository;

import com.svalero.practicasfeb.model.Car;
import com.svalero.practicasfeb.model.Sale;
import com.svalero.practicasfeb.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;



public class SaleDAO {

    public static List<Sale> sales = new ArrayList<>();

    public void save(Sale sale) throws Exception {

        String sql = """
            INSERT INTO coche (codigo_factura, fecha_venta, precio_final, garantia, es_financiado)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sale.getCodigoFactura());

            if (sale.getFechaVenta() != null) {
                ps.setDate(2, java.sql.Date.valueOf(sale.getFechaVenta()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }

            ps.setFloat(3, sale.getPrecioFinal());
            ps.setInt(4, sale.getGarantia());
            ps.setBoolean(5, sale.isFinanciado());

            System.out.println("Intentando guardar venta...");

            ps.executeUpdate();
        }
    }

    public void update(Sale sale) throws Exception {

        String sql = """
                UPDATE Venta
                SET FechaVenta = ?, PrecioFinal = ?, Garantia = ?, EsFinanciado = ?
                WHERE CodigoFactura = ?
        """;

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setFloat(2, sale.getPrecioFinal());
            ps.setInt(3, sale.getGarantia());
            ps.setBoolean(4, sale.isFinanciado());
            ps.setString(1, sale.getCodigoFactura());

            System.out.println("Intentando modificar venta...");

            ps.executeUpdate();
        }
    }

    public void delete(String codigoFactura) throws Exception {

        String sql = "DELETE FROM venta WHERE codigo_factura = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigoFactura);
            ps.executeUpdate();
        }
    }

    public Sale findByCodigoFactura(int codigoFactura) throws Exception {

        String sql = "SELECT * FROM venta WHERE codigoFactura = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigoFactura);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                java.sql.Date sqlDate = rs.getDate("FechaVenta");
                java.time.LocalDate fecha =
                        (sqlDate != null) ? sqlDate.toLocalDate() : null;

                return new Sale(
                        rs.getString("CodigoFactura"),
                        fecha,
                        rs.getFloat("PrecioFinal"),
                        rs.getInt("Garantia"),
                        rs.getBoolean("EsFinanciado")
                );
            }
        }
        return null;
    }

    public static List<Sale> findAll() throws Exception {

        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM Venta";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                java.sql.Date sqlDate = rs.getDate("FechaVenta");
                java.time.LocalDate fecha =
                        (sqlDate != null) ? sqlDate.toLocalDate() : null;

                sales.add(new Sale(
                        rs.getString("CodigoFactura"),
                        fecha,
                        rs.getFloat("PrecioFinal"),
                        rs.getInt("Garantia"),
                        rs.getBoolean("EsFinanciado")
                ));
            }
        }
        return sales;
    }
}
