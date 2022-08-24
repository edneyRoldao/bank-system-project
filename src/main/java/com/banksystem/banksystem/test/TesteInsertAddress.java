package com.banksystem.banksystem.test;

import com.banksystem.banksystem.models.Address;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class TesteInsertAddress {

    public static void main(String[] args) {
        testInsert();
    }

    @SneakyThrows
    static void testInsert() {

        // obtendo uma instancia de connection (nossa conexao com o banco)
        Connection connection = getConnection();

        // enreco fake para teste
        var address = new Address(null, 22343030, "Rio Grande do Norte", "SP", "rua mere mere", 472, "apto 8");

        // nossa query
        String QUERY = "INSERT INTO address (city, state, address, house_number, cep, address_2) VALUES (?,?,?,?,?,?)";

        // aqui temos o objeto que vai executar as queries no banco
        PreparedStatement stmt = connection.prepareStatement(QUERY);

        // aqui sao os replacements das interrogacoes que estao na query
        stmt.setString(1, address.getCity());
        stmt.setString(2, address.getState());
        stmt.setString(3, address.getAddress());
        stmt.setInt(4, address.getHouseNumber());
        stmt.setInt(5, address.getCep());
        stmt.setString(6, address.getSecondAddress());

        // executando a query
        stmt.executeUpdate();

        // obtendo o id que foi criado
        Long addressId = getCreatedAddressId();


        address.setId(addressId);

        System.out.println(address);
    }

    @SneakyThrows
    static Long getCreatedAddressId() {
        long id = 0;
        Connection connection = getConnection();

        String QUERY = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = 'address'";
        PreparedStatement stmt = connection.prepareStatement(QUERY);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getLong("AUTO_INCREMENT");
        }

        return id - 1;
    }

}
