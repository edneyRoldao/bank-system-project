package com.banksystem.banksystem.repositories;

import java.util.Optional;

public interface AccessRepository {

    String GET_PASSWORD_SQL = "SELECT a.password FROM access a WHERE a.client_id = ? AND a.account_id = ?";
    String INSERT_ACCESS_SQL = "INSERT INTO access (client_id, account_id, password) VALUES (?,?,?)";

    Optional<String> getPassword(Long clientId, Long accountId);

    void createPassword(String password, Long clientId, Long accountId);

}
