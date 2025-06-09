package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class Database {
    private static final HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://10.0.0.127:3306/betterdziekanat");
        config.setUsername("root");
        config.setPassword("Kebab123");
        config.addDataSourceProperty("characterEncoding", "utf8");
        config.addDataSourceProperty("useUnicode", "true");
        ds = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return ds;
    }
}
