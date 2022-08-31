package com.banksystem.banksystem;

import com.banksystem.banksystem.exceptions.BankAccountSystemException;
import com.banksystem.banksystem.models.BankAccount;

import java.util.Scanner;

public class SecondOption {

    public static void deposito(BankSystemStartApplication app) {
        Scanner in = new Scanner(System.in);

        System.out.println("OPCAO: depósito selecionada!");
        System.out.println("Informe a agencia:");
        int agencia = in.nextInt();

        System.out.println("Informe a conta para deposito:");
        long numeroConta = in.nextLong();

        BankAccount bankAccount = app
                .bankAccountService.getAccount(agencia, numeroConta)
                .orElseThrow(() -> new BankAccountSystemException("conta informada nao existe"));

        if (bankAccount.isAccountNotActive()) {
            System.out.println("A conta informada nao esta ativada");
            return;
        }

        System.out.println("Informe o valor a ser depositado:");
        double valor = in.nextDouble();

        app.bankAccountService.deposit(bankAccount, valor);

        System.out.println("deposito realizado com sucesso");
        System.out.println("fim do processo");
    }

}
