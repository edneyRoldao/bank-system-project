package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.BankAccount;

public interface BankAccountRepository {

    String INSERT_ACCOUNT_SQL = "INSERT INTO bank_account (account_number, client_id, registration_dt) VALUES (?,?,?)";
    String NEXT_ACCOUNT_ID_SQL = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = 'bank_account'";
    String GET_ACCOUNT_BY_EMAIL_SQL = "SELECT ba.* FROM bank_account ba INNER JOIN client c ON (c.id = ba.client_id) WHERE c.email = ?";

    BankAccount createBankAccount(BankAccount bankAccount);

    BankAccount findAccountByEmail(String email);

}
