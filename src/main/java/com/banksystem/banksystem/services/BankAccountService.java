package com.banksystem.banksystem.services;

import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.models.Client;

import java.util.Optional;

public interface BankAccountService {

    Optional<BankAccount> bankAccountAlreadyExists(Client client);

    BankAccount createBankAccount(BankAccount bankAccount);

    Optional<BankAccount> getAccount(Integer agency, Long accountNumber);

    void deposit(BankAccount bankAccount, double amount);

}
