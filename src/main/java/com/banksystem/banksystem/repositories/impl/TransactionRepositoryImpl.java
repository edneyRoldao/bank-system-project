package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.models.Transaction;
import com.banksystem.banksystem.repositories.TransactionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;
import static com.banksystem.banksystem.utils.DateUtil.localDateTimeToTimestamp;

public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public void saveTransaction(Transaction transaction) {
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(SAVE_TRANSACTION_SQL);
            statement.setDouble(1, transaction.getValue());
            statement.setString(2, transaction.getOperation().name());
            statement.setTimestamp(3, localDateTimeToTimestamp(transaction.getOperationDate()));
            statement.setLong(4, transaction.getAccountId());
            statement.setString(5, transaction.getType().name());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
