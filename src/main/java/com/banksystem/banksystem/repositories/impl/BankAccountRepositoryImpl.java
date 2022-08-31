package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.enums.AccountType;
import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.repositories.BankAccountRepository;
import com.banksystem.banksystem.utils.DateUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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

        long id = getId("bank_account");

        bankAccount.setId(id);

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
            return buildFromResultSet(resultSet);
        }

        return null;
    }

    @Override
    @SneakyThrows
    public BankAccount getBankAccount(Integer agency, Long accountNumber) {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_ACCOUNT_SQL);
        statement.setInt(1, agency);
        statement.setLong(2, accountNumber);
        ResultSet resultSet = statement.executeQuery();

        BankAccount bankAccount = null;

        while (resultSet.next()) {
            bankAccount = buildFromResultSet(resultSet);
        }

        return bankAccount;
    }

    @Override
    @SneakyThrows
    public void updateBalance(BankAccount bankAccount) {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE_SQL);
        statement.setDouble(1, bankAccount.getBalance());
        statement.setLong(2, bankAccount.getId());
        statement.executeUpdate();
    }

    @SneakyThrows
    private BankAccount buildFromResultSet(ResultSet resultSet) {
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


}
