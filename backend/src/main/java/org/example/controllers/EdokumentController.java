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
}

