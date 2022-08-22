package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.models.Address;
import com.banksystem.banksystem.repositories.AddressRepository;
import com.banksystem.banksystem.utils.ApplicationProperties;

import java.sql.*;

public class AddressRepositoryImpl implements AddressRepository {

    private static final String INSERT_ADDRESS_SQL =
            "INSERT INTO address (city, state, address, house_number, cep, address_2) VALUES (?,?,?,?,?,?)";

    private static final String NEXT_ADDRESS_ID_SQL =
            "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = 'address'";

    @Override
    public Long insertAddress(Address address) {
        String url = ApplicationProperties.DB_CONNECTION;
        String user = ApplicationProperties.DB_USERNAME;
        String pass = ApplicationProperties.DB_PASSWORD;

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement stmt = connection.prepareStatement(INSERT_ADDRESS_SQL);

            stmt.setString(1, address.getCity());
            stmt.setString(2, address.getState());
            stmt.setString(3, address.getAddress());
            stmt.setInt(4, address.getHouseNumber());
            stmt.setInt(5, address.getCep());
            stmt.setString(6, address.getSecondAddress());
            stmt.executeUpdate();
            connection.close();

            return getAddressId();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    private long getAddressId() {
        long addressId = 0;
        String url = ApplicationProperties.DB_CONNECTION;
        String user = ApplicationProperties.DB_USERNAME;
        String pass = ApplicationProperties.DB_PASSWORD;


        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = connection.prepareStatement(NEXT_ADDRESS_ID_SQL);
            ResultSet resultSet = statement.executeQuery();
            long nextId = resultSet.getLong("auto_increment");
            addressId = nextId - 1;
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressId;
    }

    @Override
    public Address getAddressById(Long id) {
        return null;
    }

}
