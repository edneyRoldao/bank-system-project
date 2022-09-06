package com.banksystem.banksystem;

import com.banksystem.banksystem.exceptions.BankAccountSystemException;
import com.banksystem.banksystem.models.BankAccount;
import com.banksystem.banksystem.services.AccessService;
import com.banksystem.banksystem.services.AddressService;
import com.banksystem.banksystem.services.BankAccountService;
import com.banksystem.banksystem.services.ClientService;
import com.banksystem.banksystem.services.impl.AccessServiceImpl;
import com.banksystem.banksystem.services.impl.AddressServiceImpl;
import com.banksystem.banksystem.services.impl.BankAccountServiceImpl;
import com.banksystem.banksystem.services.impl.ClientServiceImpl;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class BankSystemStartApplication {

	final AccessService accessService;
	final ClientService clientService;
	final AddressService addressService;
	final BankAccountService bankAccountService;

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
				FirstOption.criarConta(app);
				break;
			case 2:
				SecondOption.deposito(app);
				break;
			case 3:
				ThirdOption.sacar(app);
				break;
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
