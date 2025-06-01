package org.example.controllers;

import org.example.db.Database;
import io.javalin.http.Context;

import java.sql.*;
import java.util.*;

public class OcenaController {

    public static void getAll(Context ctx) throws SQLException {
        List<Map<String, Object>> oceny = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Oceny")) {
            while (rs.next()) {
                Map<String, Object> ocena = new HashMap<>();
                ocena.put("id", rs.getInt("id"));
                ocena.put("student_zajecia_id", rs.getInt("student_zajecia_id"));
                ocena.put("wartosc", rs.getString("wartosc"));
                ocena.put("data_wystawienia", rs.getTimestamp("data_wystawienia"));
                ocena.put("wystawiajacy_id", rs.getInt("wystawiajacy_id"));
                oceny.add(ocena);
            }
        }
        ctx.json(oceny);
    }
    public static void create(Context ctx) throws SQLException {
        Map<String, Object> ocena = ctx.bodyAsClass(Map.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO Oceny (student_zajecia_id, wartosc, data_wystawienia, wystawiajacy_id) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, (Integer) ocena.get("student_zajecia_id"));
            ps.setString(2, (String) ocena.get("wartosc"));
            ps.setTimestamp(3, Timestamp.valueOf((String) ocena.get("data_wystawienia")));
            ps.setInt(4, (Integer) ocena.get("wystawiajacy_id"));
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                ocena.put("id", keys.getInt(1));
            }
        }
        ctx.status(201).json(ocena);
    }

    public static void getById(Context ctx) throws SQLException {
        int ocenaId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Oceny WHERE id = ?")) {
            ps.setInt(1, ocenaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Map<String, Object> ocena = new HashMap<>();
                ocena.put("id", rs.getInt("id"));
                ocena.put("student_zajecia_id", rs.getInt("student_zajecia_id"));
                ocena.put("wartosc", rs.getString("wartosc"));
                ocena.put("data_wystawienia", rs.getTimestamp("data_wystawienia"));
                ocena.put("wystawiajacy_id", rs.getInt("wystawiajacy_id"));
                ctx.json(ocena);
            } else {
                ctx.status(404).result("Ocena nie znaleziona.");
            }
        }
    }

    public static void update(Context ctx) throws SQLException {
        int ocenaId = Integer.parseInt(ctx.pathParam("id"));
        Map<String, Object> ocena = ctx.bodyAsClass(Map.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE Oceny SET student_zajecia_id = ?, wartosc = ?, data_wystawienia = ?, wystawiajacy_id = ? WHERE id = ?")) {
            ps.setInt(1, (Integer) ocena.get("student_zajecia_id"));
            ps.setString(2, (String) ocena.get("wartosc"));
            ps.setTimestamp(3, Timestamp.valueOf((String) ocena.get("data_wystawienia")));
            ps.setInt(4, (Integer) ocena.get("wystawiajacy_id"));
            ps.setInt(5, ocenaId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                ctx.status(200).result("Ocena zaktualizowana.");
            } else {
                ctx.status(404).result("Ocena nie znaleziona.");
            }
        }
    }

    public static void delete(Context ctx) throws SQLException {
        int ocenaId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Oceny WHERE id = ?")) {
            ps.setInt(1, ocenaId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                ctx.status(200).result("Ocena usunięta.");
            } else {
                ctx.status(404).result("Ocena nie znaleziona.");
            }
        }
    }

    public static void getAverage(Context ctx) throws SQLException {
        int studentZajeciaId = Integer.parseInt(ctx.pathParam("student_zajecia_id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT AVG(CAST(wartosc AS DECIMAL)) AS srednia FROM Oceny WHERE student_zajecia_id = ?")) {
            ps.setInt(1, studentZajeciaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double srednia = rs.getDouble("srednia");
                ctx.json(Map.of("student_zajecia_id", studentZajeciaId, "srednia", srednia));
            } else {
                ctx.status(404).result("Brak ocen dla podanego studenta.");
            }
        }
    }

    public static void getOverallAverage(Context ctx) throws SQLException {
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT AVG(CAST(wartosc AS DECIMAL)) AS srednia FROM Oceny")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double srednia = rs.getDouble("srednia");
                ctx.json(Map.of("srednia", srednia));
            } else {
                ctx.status(404).result("Brak ocen w bazie danych.");
            }
        }
    }
}
