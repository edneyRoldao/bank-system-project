package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.BankAccount;

public interface BankAccountRepository extends Repository {

    String INSERT_ACCOUNT_SQL = "INSERT INTO bank_account (account_number, client_id, registration_dt) VALUES (?,?,?)";
    String GET_ACCOUNT_BY_EMAIL_SQL = "SELECT ba.* FROM bank_account ba INNER JOIN client c ON (c.id = ba.client_id) WHERE c.email = ?";
    String UPDATE_BALANCE_SQL = "UPDATE bank_account ba SET ba.balance = ? WHERE ba.id = ?";
    String GET_ACCOUNT_SQL = "SELECT * FROM bank_account ba WHERE ba.agency = ? AND ba.account_number = ?";

    BankAccount createBankAccount(BankAccount bankAccount);

    BankAccount findAccountByEmail(String email);

    BankAccount getBankAccount(Integer agency, Long accountNumber);

    void updateBalance(BankAccount bankAccount);

}
