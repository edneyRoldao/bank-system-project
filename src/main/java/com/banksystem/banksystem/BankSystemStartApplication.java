package com.banksystem.banksystem;

import com.banksystem.banksystem.enums.AccountType;
import com.banksystem.banksystem.models.Address;
import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.models.Client;
import com.banksystem.banksystem.services.AccessService;
import com.banksystem.banksystem.services.AddressService;
import com.banksystem.banksystem.services.BankAccountService;
import com.banksystem.banksystem.services.ClientService;
import com.banksystem.banksystem.services.impl.AccessServiceImpl;
import com.banksystem.banksystem.services.impl.AddressServiceImpl;
import com.banksystem.banksystem.services.impl.BankAccountServiceImpl;
import com.banksystem.banksystem.services.impl.ClientServiceImpl;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class BankSystemStartApplication {

	private final AccessService accessService;
	private final ClientService clientService;
	private final AddressService addressService;
	private final BankAccountService bankAccountService;


	public BankSystemStartApplication() {
		accessService = new AccessServiceImpl();
		clientService = new ClientServiceImpl();
		addressService = new AddressServiceImpl();
		bankAccountService = new BankAccountServiceImpl();
	}


	public static void main(String[] args) {
		BankSystemStartApplication app = new BankSystemStartApplication();

		int option = 0;

		while (option == 0) {
			option = showAndGetMenuOption();
			if (option == 0) {
				System.out.println("*************************************************************");
				System.out.println("*****  opção inválida: Favor inserir um numero de 1 a 9 *****");
				System.out.println("*************************************************************");
			}
		}

		switch (option) {
			case 1:
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

				break;
			case 2:
				System.out.println("Depositar - em construcao");
				break;
			case 3:
				System.out.println("Sacar - em costrucao");
			case 9:
				System.out.println("##########################################");
				System.out.println("Obrigado por usar esta simplório programa.");
				System.out.println("##########################################");
				break;
		}

	}

	private static int showAndGetMenuOption() {
		System.out.println("####################################################");
		System.out.println("####################################################");
		System.out.println("---------------- BANK SYSTEM MENU -------------------");
		System.out.println("####################################################");
		System.out.println("Selecione uma Opção:");
		System.out.println("01 - Criar conta bancária");
		System.out.println("02 - Depositar");
		System.out.println("03 - Sacar");
		System.out.println("04 - Ativa conta");
		System.out.println("05 - Desativar conta");
		System.out.println("06 - Extrato");
		System.out.println("07 - Transferencia");
		System.out.println("08 - Consultar Saldo");
		System.out.println("09 - Sair");
		System.out.println("####################################################");
		System.out.println("####################################################");

		try {
			int opt = new Scanner(System.in).nextInt();
			return opt > 0 && opt <= 9 ? opt : 0;

		} catch (InputMismatchException e) {
			return 0;
		}
	}

}
