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
}
