package org.example.controllers;


import org.example.db.Database;
import io.javalin.http.Context;

import java.sql.*;
import java.util.*;

public class ZajeciaController {

    public static void getAll(Context ctx) throws SQLException {
        List<Map<String, Object>> zajecia = new ArrayList<>();
        try (Connection conn = Database.getDataSource().getConnection();
             ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Zajecia")) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("nazwa", rs.getString("nazwa"));
                row.put("opis", rs.getString("opis"));
                row.put("data_rozpoczecia", rs.getTimestamp("data_rozpoczecia"));
                row.put("data_zakonczenia", rs.getTimestamp("data_zakonczenia"));
                row.put("prowadzacy_id", rs.getInt("prowadzacy_id"));
                row.put("sala_id", rs.getInt("sala_id"));
                zajecia.add(row);
            }
        }
        ctx.json(zajecia);
    }
}
