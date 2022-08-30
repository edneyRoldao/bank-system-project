package com.banksystem.banksystem.services;

public interface AccessService {

    void createAccess(String password, Long clientId, Long accountId);

    boolean isPasswordRight(String password, Long clientId, Long accountId);

}
