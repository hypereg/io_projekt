package org.example.controllers;

import org.example.db.Database;
import org.example.models.User;
import io.javalin.http.Context;

import java.sql.*;

public class AuthController {

    public static void login(Context ctx) throws SQLException {
        User creds = ctx.bodyAsClass(User.class);
        try (Connection conn = Database.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Uzytkownicy WHERE email = ? AND haslo = ?")) {
            ps.setString(1, creds.email);
            ps.setString(2, creds.haslo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.id = rs.getInt("id");
                u.imie = rs.getString("imie");
                u.nazwisko = rs.getString("nazwisko");
                u.email = rs.getString("email");
                u.rola = rs.getString("rola");
                u.numerBetterIndex = rs.getString("numer_better_index");
                ctx.json(u);
            } else {
                ctx.status(401).result("Nieprawidłowy email lub hasło.");
            }
        }
    }
}
