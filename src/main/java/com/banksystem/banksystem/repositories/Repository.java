package com.banksystem.banksystem.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public interface Repository {

    String GET_NEXT_ID_SQL = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = ?";

    default long getId(String tableName) {
        long clientId = 0;

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_NEXT_ID_SQL);

            statement.setString(1, tableName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long nextId = resultSet.getLong("AUTO_INCREMENT");
                clientId = nextId - 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientId;
    }

}
