package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.Client;

public interface ClientRepository extends Repository {

    String INSERT_CLIENT_SQL = "INSERT INTO client (name, birthdate, phone, email, document, address_id) VALUES (?,?,?,?,?,?)";

    Client createClient(Client client);

}
