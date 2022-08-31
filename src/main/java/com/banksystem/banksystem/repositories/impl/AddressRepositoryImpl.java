package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.models.Address;
import com.banksystem.banksystem.repositories.AddressRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class AddressRepositoryImpl implements AddressRepository {

    @Override
    public Address createAddress(Address address) {

        try {
            Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_ADDRESS_SQL);

            stmt.setString(1, address.getCity());
            stmt.setString(2, address.getState());
            stmt.setString(3, address.getAddress());
            stmt.setInt(4, address.getHouseNumber());
            stmt.setInt(5, address.getCep());
            stmt.setString(6, address.getSecondAddress());
            stmt.executeUpdate();

            long id = getId("address");

            address.setId(id);

            return address;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
