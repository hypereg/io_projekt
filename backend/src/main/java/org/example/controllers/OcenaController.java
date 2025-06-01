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
}
