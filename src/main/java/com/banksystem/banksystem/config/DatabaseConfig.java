package com.banksystem.banksystem.config;

import com.banksystem.banksystem.utils.ApplicationProperties;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

// Singleton (design pattern)
public class DatabaseConfig {

    private static Connection connection;

    @SneakyThrows
    public static Connection getConnection() {
        if (Objects.nonNull(connection) && !connection.isClosed()) {
            return connection;
        }

        String user = ApplicationProperties.DB_USERNAME;
        String pass = ApplicationProperties.DB_PASSWORD;
        String url = ApplicationProperties.DB_CONNECTION;
        connection = DriverManager.getConnection(url, user, pass);

        return connection;
    }

}
