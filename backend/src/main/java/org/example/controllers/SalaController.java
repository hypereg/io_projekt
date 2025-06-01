package org.example.controllers;


import org.example.db.Database;
import io.javalin.http.Context;

import java.sql.*;
import java.util.*;

public class SalaController {

    public static void getAll(Context ctx) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Sale")) {
            while (rs.next()) {
                Map<String, Object> sala = new HashMap<>();
                sala.put("id", rs.getInt("id"));
                sala.put("nazwa", rs.getString("nazwa"));
                sala.put("budynek", rs.getString("budynek"));
                sala.put("pojemnosc", rs.getInt("pojemnosc"));
                list.add(sala);
            }
        }
        ctx.json(list);
    }
    public static void create(Context ctx) throws SQLException {
        Map<String, Object> sala = ctx.bodyAsClass(Map.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO Sale (nazwa, budynek, pojemnosc) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, (String) sala.get("nazwa"));
            ps.setString(2, (String) sala.get("budynek"));
            ps.setInt(3, (Integer) sala.get("pojemnosc"));
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                sala.put("id", keys.getInt(1));
            }
        }
        ctx.status(201).json(sala);
    }

    public static void getById(Context ctx) throws SQLException {
        int salaId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Sale WHERE id = ?")) {
            ps.setInt(1, salaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Map<String, Object> sala = new HashMap<>();
                sala.put("id", rs.getInt("id"));
                sala.put("nazwa", rs.getString("nazwa"));
                sala.put("budynek", rs.getString("budynek"));
                sala.put("pojemnosc", rs.getInt("pojemnosc"));
                ctx.json(sala);
            } else {
                ctx.status(404).result("Sala nie znaleziona.");
            }
        }
    }

    public static void update(Context ctx) throws SQLException {
        int salaId = Integer.parseInt(ctx.pathParam("id"));
        Map<String, Object> sala = ctx.bodyAsClass(Map.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE Sale SET nazwa = ?, budynek = ?, pojemnosc = ? WHERE id = ?")) {
            ps.setString(1, (String) sala.get("nazwa"));
            ps.setString(2, (String) sala.get("budynek"));
            ps.setInt(3, (Integer) sala.get("pojemnosc"));
            ps.setInt(4, salaId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                ctx.status(200).result("Sala zaktualizowana.");
            } else {
                ctx.status(404).result("Sala nie znaleziona.");
            }
        }
    }

    public static void delete(Context ctx) throws SQLException {
        int salaId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Sale WHERE id = ?")) {
            ps.setInt(1, salaId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                ctx.status(200).result("Sala usunięta.");
            } else {
                ctx.status(404).result("Sala nie znaleziona.");
            }
        }
    }
}
