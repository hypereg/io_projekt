package org.example.controllers;

import org.example.db.Database;
import org.example.models.User;
import io.javalin.http.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static void getAll(Context ctx) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Uzytkownicy")) {
            while (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.imie = rs.getString("imie");
                u.nazwisko = rs.getString("nazwisko");
                u.email = rs.getString("email");
                u.haslo = rs.getString("haslo");
                u.rola = rs.getString("rola");
                u.numerBetterIndex = rs.getString("numer_better_index");
                users.add(u);
            }
        }
        ctx.json(users);
    }

    public static void create(Context ctx) throws SQLException {
        User user = ctx.bodyAsClass(User.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO Uzytkownicy (imie, nazwisko, email, haslo, rola, numer_better_index) VALUES (?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.imie);
            ps.setString(2, user.nazwisko);
            ps.setString(3, user.email);
            ps.setString(4, user.haslo);
            ps.setString(5, user.rola);
            ps.setString(6, user.numerBetterIndex);
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                user.id = keys.getInt(1);
            }
        }
        ctx.status(201).json(user);
    }
    public static void getById(Context ctx) throws SQLException {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE id = ?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.imie = rs.getString("imie");
                u.nazwisko = rs.getString("nazwisko");
                u.email = rs.getString("email");
                u.haslo = rs.getString("haslo");
                u.rola = rs.getString("rola");
                u.numerBetterIndex = rs.getString("numer_better_index");
                ctx.json(u);
            } else {
                ctx.status(404).result("Użytkownik nie znaleziony.");
            }
        }
    }
    public static void update(Context ctx) throws SQLException {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        User user = ctx.bodyAsClass(User.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE Uzytkownicy SET imie = ?, nazwisko = ?, email = ?, haslo = ?, rola = ?, numer_better_index = ? WHERE id = ?")) {
            ps.setString(1, user.imie);
            ps.setString(2, user.nazwisko);
            ps.setString(3, user.email);
            ps.setString(4, user.haslo);
            ps.setString(5, user.rola);
            ps.setString(6, user.numerBetterIndex);
            ps.setInt(7, userId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                ctx.status(200).result("Użytkownik zaktualizowany.");
            } else {
                ctx.status(404).result("Użytkownik nie znaleziony.");
            }
        }
    }
    public static void delete(Context ctx) throws SQLException {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Uzytkownicy WHERE id = ?")) {
            ps.setInt(1, userId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                ctx.status(200).result("Użytkownik usunięty.");
            } else {
                ctx.status(404).result("Użytkownik nie znaleziony.");
            }
        }
    }
}