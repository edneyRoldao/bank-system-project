package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.repositories.AccessRepository;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class AccessRepositoryImpl implements AccessRepository {

    @Override
    @SneakyThrows
    public Optional<String> getPassword(Long clientId, Long accountId) {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(GET_PASSWORD_SQL);
        stmt.setLong(1, clientId);
        stmt.setLong(2, accountId);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            return Optional.of(resultSet.getString("password"));
        }

        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public void createPassword(String password, Long clientId, Long accountId) {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_ACCESS_SQL);

        stmt.setLong(1, clientId);
        stmt.setLong(2, accountId);
        stmt.setString(3, password);

        stmt.executeUpdate();
    }

}
