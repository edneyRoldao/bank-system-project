package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.Transaction;

public interface TransactionRepository {

    String SAVE_TRANSACTION_SQL = "INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp) VALUES (?,?,?,?,?)";

    void saveTransaction(Transaction transaction);

}
