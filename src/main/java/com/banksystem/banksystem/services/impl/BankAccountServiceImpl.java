package com.banksystem.banksystem.services.impl;

import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.models.Client;
import com.banksystem.banksystem.repositories.BankAccountRepository;
import com.banksystem.banksystem.repositories.impl.BankAccountRepositoryImpl;
import com.banksystem.banksystem.services.BankAccountService;

import java.util.Objects;
import java.util.Optional;

public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountServiceImpl() {
        this.repository = new BankAccountRepositoryImpl();
    }

    @Override
    public Optional<BankAccount> bankAccountAlreadyExists(Client client) {
        BankAccount bankAccount = repository.findAccountByEmail(client.getEmail());
        return Objects.nonNull(bankAccount) ? Optional.of(bankAccount) : Optional.empty();
    }

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        return repository.createBankAccount(bankAccount);
    }

}
