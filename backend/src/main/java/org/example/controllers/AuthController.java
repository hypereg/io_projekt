package org.example.controllers;

import org.example.db.Database;
import org.example.models.User;
import org.example.util.JwtUtil;
import io.javalin.http.Context;

import java.sql.*;
import java.util.Map;

public class AuthController {

    public static void register(Context ctx) throws SQLException {
        User newUser = ctx.bodyAsClass(User.class);
        try (Connection conn = Database.getDataSource().getConnection()) {
            try (PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE email = ?")) {
                checkStmt.setString(1, newUser.getEmail());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    ctx.status(400).result("Użytkownik o tym email juz istneije");
                    return;
                }
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Uzytkownicy (imie, nazwisko, email, haslo, rola, numer_better_index) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, newUser.getImie());
                ps.setString(2, newUser.getNazwisko());
                ps.setString(3, newUser.getEmail());
                ps.setString(4, newUser.getHaslo());
                ps.setString(5, newUser.getRola());
                ps.setString(6, newUser.getNumerBetterIndex());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    newUser.setId(keys.getInt(1));
                }
            }
            ctx.status(201).json(newUser);
        }
    }

    public static void login(Context ctx) throws SQLException {
        User creds = ctx.bodyAsClass(User.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE email = ? AND haslo = ?")) {
            ps.setString(1, creds.email);
            ps.setString(2, creds.haslo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String token = JwtUtil.generateToken(creds.email);
                ctx.json(Map.of("token", token));
            } else {
                ctx.status(401).result("Nieprawidlowy email lb haslo");
            }
        }
    }

    public static void verifyToken(Context ctx) {
        String token = ctx.header("Authorization");
        if (token == null || !JwtUtil.validateToken(token)) {
            ctx.status(401).result("Nie uprawniony odstep.");
            return;
        }
        String email = JwtUtil.getEmailFromToken(token);
        ctx.status(200).result("Token prawidlowy email: " + email);
    }
}