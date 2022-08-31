package com.banksystem.banksystem;

import com.banksystem.banksystem.enums.AccountType;
import com.banksystem.banksystem.models.Address;
import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.models.Client;

import java.util.Optional;
import java.util.Scanner;

public class FirstOption {

    public static void criarConta(BankSystemStartApplication app) {
        Scanner input = new Scanner(System.in);

        System.out.println("Informe dados do cliente:");
        System.out.println("formato: nome, email, telefone, documento, data nascimento(dd/MM/yyyy)");
        String dadosCliente = input.nextLine();
        Optional<Client> clientOptional = app.clientService.validateAndBuildClient(dadosCliente);

        if (clientOptional.isEmpty()) {
            System.out.println("dados cliente inválido");
            return;
        }

        Client client = clientOptional.get();
        Optional<BankAccount> bankAccountOpt = app.bankAccountService.bankAccountAlreadyExists(client);

        if (bankAccountOpt.isPresent()) {
            BankAccount bankAccount = bankAccountOpt.get();
            String message = "Esse cliente já possui conta nesse banco, o numero da conta é: %s \n";
            System.out.printf(message, bankAccount.getAccountNumber());
            return;
        }

        System.out.println("Informe seu endereço:");
        System.out.println("formato: rua, numero, cidade, estado, cep, complemento(opcional)");
        System.out.println("exemplo: rua arnaldo, 22, São Paulo, SP, 02577000, casa 1");
        String addressString = input.nextLine();
        Optional<Address> addressOpt = app.addressService.buildAddress(addressString);

        if (addressOpt.isEmpty()) {
            System.out.println("endereco inválido");
            return;
        }

        Address address = addressOpt.get();
        Address addressSaved = app.addressService.createAddress(address);

        client.setAddressId(addressSaved.getId());
        System.out.println(client);

        Client clientSaved = app.clientService.createClient(client);
        BankAccount bankAccount = new BankAccount(clientSaved.getId(), AccountType.CORRENTE);
        BankAccount bankAccountSaved = app.bankAccountService.createBankAccount(bankAccount);

        System.out.println("Informe a senha de acesso para a conta:");
        String senha = input.nextLine();

        app.accessService.createAccess(senha, bankAccountSaved.getClientId(), bankAccountSaved.getId());

        System.out.println("#### conta criada com sucesso");
        System.out.printf("Agencia: %s \n", bankAccountSaved.getAgency());
        System.out.printf("Numero da conta: %s \n", bankAccountSaved.getAccountNumber());
        System.out.println("fim do processo");
    }

}
