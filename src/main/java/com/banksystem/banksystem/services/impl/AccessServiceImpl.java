package com.banksystem.banksystem.services.impl;

import com.banksystem.banksystem.exceptions.BankAccountSystemException;
import com.banksystem.banksystem.repositories.AccessRepository;
import com.banksystem.banksystem.repositories.impl.AccessRepositoryImpl;
import com.banksystem.banksystem.services.AccessService;

public class AccessServiceImpl implements AccessService {

    private final AccessRepository repository;

    public AccessServiceImpl() {
        this.repository = new AccessRepositoryImpl();
    }

    @Override
    public void createAccess(String password, Long clientId, Long accountId) {
        // delegar ou by pass
        repository.createPassword(password, clientId, accountId);
    }

    @Override
    public boolean isPasswordRight(String passwordProvided, Long clientId, Long accountId) {
        String passwordFromDatabase = repository.getPassword(clientId, accountId)
                .orElseThrow(() -> new BankAccountSystemException("senha do cliente nao foi cadastrada. Entre em contato com a administrador do sistema"));
        return passwordProvided.equals(passwordFromDatabase);
    }

}
