package com.banksystem.banksystem.services.impl;

import com.banksystem.banksystem.enums.Operation;
import com.banksystem.banksystem.enums.TransactionType;
import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.models.Client;
import com.banksystem.banksystem.models.Transaction;
import com.banksystem.banksystem.repositories.BankAccountRepository;
import com.banksystem.banksystem.repositories.TransactionRepository;
import com.banksystem.banksystem.repositories.impl.BankAccountRepositoryImpl;
import com.banksystem.banksystem.repositories.impl.TransactionRepositoryImpl;
import com.banksystem.banksystem.services.BankAccountService;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository repository;
    private final TransactionRepository transactionRepository;

    public BankAccountServiceImpl() {
        this.repository = new BankAccountRepositoryImpl();
        this.transactionRepository = new TransactionRepositoryImpl();
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

    @Override
    public Optional<BankAccount> getAccount(Integer agency, Long accountNumber) {
        BankAccount bankAccount = repository.getBankAccount(agency, accountNumber);
        return Objects.nonNull(bankAccount) ? Optional.of(bankAccount) : Optional.empty();
    }

    @Override
    public void deposit(BankAccount bankAccount, double amount) {
        double balance = bankAccount.getBalance();
        double total = balance + amount;
        bankAccount.setBalance(total);
        repository.updateBalance(bankAccount);

        Transaction transaction = Transaction
                .builder()
                .value(amount)
                .accountId(bankAccount.getId())
                .operation(Operation.ENTRADA)
                .type(TransactionType.DEPOSIT)
                .operationDate(LocalDateTime.now())
                .build();

        transactionRepository.saveTransaction(transaction);
    }

}
