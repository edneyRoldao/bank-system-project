package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.Client;

public interface ClientRepository {

    String INSERT_CLIENT_SQL = "INSERT INTO client (name, birthdate, phone, email, document, address_id) VALUES (?,?,?,?,?,?)";
    String NEXT_CLIENT_ID_SQL = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = 'client'";

    Client createClient(Client client);

}
