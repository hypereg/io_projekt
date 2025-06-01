package org.example.controllers;

import org.example.db.Database;
import io.javalin.http.Context;

import java.sql.*;
import java.util.*;

public class EdokumentController {

    public static void getAll(Context ctx) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM E_dokumenty")) {
            while (rs.next()) {
                Map<String, Object> doc = new HashMap<>();
                doc.put("id", rs.getInt("id"));
                doc.put("tytul", rs.getString("tytul"));
                doc.put("typ", rs.getString("typ"));
                doc.put("sciezka", rs.getString("sciezka"));
                doc.put("data_utworzenia", rs.getTimestamp("data_utworzenia"));
                doc.put("uzytkownik_id", rs.getInt("uzytkownik_id"));
                list.add(doc);
            }
        }
        ctx.json(list);
    }
    public static void create(Context ctx) throws SQLException {
        Map<String, Object> doc = ctx.bodyAsClass(Map.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO E_dokumenty (tytul, typ, sciezka, data_utworzenia, uzytkownik_id) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, (String) doc.get("tytul"));
            ps.setString(2, (String) doc.get("typ"));
            ps.setString(3, (String) doc.get("sciezka"));
            ps.setTimestamp(4, Timestamp.valueOf((String) doc.get("data_utworzenia")));
            ps.setInt(5, (Integer) doc.get("uzytkownik_id"));
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                doc.put("id", keys.getInt(1));
            }
        }
        ctx.status(201).json(doc);
    }

    public static void getById(Context ctx) throws SQLException {
        int docId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM E_dokumenty WHERE id = ?")) {
            ps.setInt(1, docId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Map<String, Object> doc = new HashMap<>();
                doc.put("id", rs.getInt("id"));
                doc.put("tytul", rs.getString("tytul"));
                doc.put("typ", rs.getString("typ"));
                doc.put("sciezka", rs.getString("sciezka"));
                doc.put("data_utworzenia", rs.getTimestamp("data_utworzenia"));
                doc.put("uzytkownik_id", rs.getInt("uzytkownik_id"));
                ctx.json(doc);
            } else {
                ctx.status(404).result("Dokument nie znaleziony.");
            }
        }
    }

    public static void update(Context ctx) throws SQLException {
        int docId = Integer.parseInt(ctx.pathParam("id"));
        Map<String, Object> doc = ctx.bodyAsClass(Map.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE E_dokumenty SET tytul = ?, typ = ?, sciezka = ?, data_utworzenia = ?, uzytkownik_id = ? WHERE id = ?")) {
            ps.setString(1, (String) doc.get("tytul"));
            ps.setString(2, (String) doc.get("typ"));
            ps.setString(3, (String) doc.get("sciezka"));
            ps.setTimestamp(4, Timestamp.valueOf((String) doc.get("data_utworzenia")));
            ps.setInt(5, (Integer) doc.get("uzytkownik_id"));
            ps.setInt(6, docId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                ctx.status(200).result("Dokument zaktualizowany.");
            } else {
                ctx.status(404).result("Dokument nie znaleziony.");
            }
        }
    }

    public static void delete(Context ctx) throws SQLException {
        int docId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM E_dokumenty WHERE id = ?")) {
            ps.setInt(1, docId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                ctx.status(200).result("Dokument usunięty.");
            } else {
                ctx.status(404).result("Dokument nie znaleziony.");
            }
        }
    }

    public static void getByUserId(Context ctx) throws SQLException {
        int userId = Integer.parseInt(ctx.pathParam("uzytkownik_id"));
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM E_dokumenty WHERE uzytkownik_id = ?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> doc = new HashMap<>();
                doc.put("id", rs.getInt("id"));
                doc.put("tytul", rs.getString("tytul"));
                doc.put("typ", rs.getString("typ"));
                doc.put("sciezka", rs.getString("sciezka"));
                doc.put("data_utworzenia", rs.getTimestamp("data_utworzenia"));
                doc.put("uzytkownik_id", rs.getInt("uzytkownik_id"));
                list.add(doc);
            }
        }
        ctx.json(list);
    }
    public static void searchByTitle(Context ctx) throws SQLException {
        String titleFragment = ctx.queryParam("title");
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM E_dokumenty WHERE tytul LIKE ?")) {
            ps.setString(1, "%" + titleFragment + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> doc = new HashMap<>();
                doc.put("id", rs.getInt("id"));
                doc.put("tytul", rs.getString("tytul"));
                doc.put("typ", rs.getString("typ"));
                doc.put("sciezka", rs.getString("sciezka"));
                doc.put("data_utworzenia", rs.getTimestamp("data_utworzenia"));
                doc.put("uzytkownik_id", rs.getInt("uzytkownik_id"));
                list.add(doc);
            }
        }
        ctx.json(list);
    }

    public static void getRecentDocuments(Context ctx) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM E_dokumenty WHERE data_utworzenia >= NOW() - INTERVAL '7 DAYS'")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> doc = new HashMap<>();
                doc.put("id", rs.getInt("id"));
                doc.put("tytul", rs.getString("tytul"));
                doc.put("typ", rs.getString("typ"));
                doc.put("sciezka", rs.getString("sciezka"));
                doc.put("data_utworzenia", rs.getTimestamp("data_utworzenia"));
                doc.put("uzytkownik_id", rs.getInt("uzytkownik_id"));
                list.add(doc);
            }
        }
        ctx.json(list);
    }
}

