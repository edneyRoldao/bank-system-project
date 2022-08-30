package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.enums.AccountType;
import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.repositories.BankAccountRepository;
import com.banksystem.banksystem.utils.DateUtil;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    @Override
    @SneakyThrows
    public BankAccount createBankAccount(BankAccount bankAccount) {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_ACCOUNT_SQL);

        stmt.setLong(1, bankAccount.getAccountNumber());
        stmt.setLong(2, bankAccount.getClientId());
        stmt.setTimestamp(3, DateUtil.localDateTimeToTimestamp(bankAccount.getRegistrationDate()));
        stmt.executeUpdate();

        bankAccount.setId(getAccountId());

        return bankAccount;
    }

    @Override
    @SneakyThrows
    public BankAccount findAccountByEmail(String email) {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_BY_EMAIL_SQL);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String accountType = resultSet.getString("account_tp");
            AccountType type = AccountType.valueOf(accountType);

            LocalDateTime registrationDt = null;
            LocalDateTime deactivationDt = null;
            Timestamp registration = resultSet.getTimestamp("registration_dt");
            Timestamp deactivation = resultSet.getTimestamp("deactivation_dt");

            if (Objects.nonNull(registration))
                registrationDt = registration.toLocalDateTime();

            if (Objects.nonNull(deactivation))
                deactivationDt = deactivation.toLocalDateTime();

            return BankAccount
                    .builder()
                    .accountType(type)
                    .registrationDate(registrationDt)
                    .deactivationDate(deactivationDt)
                    .id(resultSet.getLong("id"))
                    .agency(resultSet.getInt("agency"))
                    .balance(resultSet.getDouble("balance"))
                    .clientId(resultSet.getLong("client_id"))
                    .accountNumber(resultSet.getLong("account_number"))
                    .build();
        }

        return null;
    }

    private long getAccountId() {
        long accountId = 0;

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(NEXT_ACCOUNT_ID_SQL);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long nextId = resultSet.getLong("AUTO_INCREMENT");
                accountId = nextId - 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountId;
    }

}
