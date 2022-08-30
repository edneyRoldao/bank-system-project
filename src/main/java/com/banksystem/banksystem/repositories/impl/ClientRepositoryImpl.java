package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.models.Client;
import com.banksystem.banksystem.repositories.ClientRepository;
import com.banksystem.banksystem.utils.DateUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class ClientRepositoryImpl implements ClientRepository {

    @Override
    @SneakyThrows
    public Client createClient(Client client) {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_CLIENT_SQL);

        stmt.setString(1, client.getName());
        stmt.setDate(2, DateUtil.localDateToDate(client.getBirthdate()));
        stmt.setString(3, client.getPhone());
        stmt.setString(4, client.getEmail());
        stmt.setString(5, client.getDocument());
        stmt.setLong(6, client.getAddressId());
        stmt.executeUpdate();

        client.setId(getClientId());

        return client;
    }

    private long getClientId() {
        long clientId = 0;

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(NEXT_CLIENT_ID_SQL);
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
