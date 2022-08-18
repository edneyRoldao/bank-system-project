package com.banksystem.banksystem;

import com.banksystem.banksystem.models.Address;
import com.banksystem.banksystem.services.AddressService;
import com.banksystem.banksystem.services.impl.AddressServiceImpl;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class BankSystemStartApplication {

	public static void main(String[] args) {
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
				AddressService addressService = new AddressServiceImpl();
				System.out.println("Informe seu endereço:");
				System.out.println("formato: rua, numero, cidade, estado, cep, complemento(opcional)");
				System.out.println("exemplo: rua arnaldo, 22, São Paulo, SP, 02577000, casa 1");

				String addressString = new Scanner(System.in).nextLine();
				Optional<Address> addressOpt = addressService.buildAddress(addressString);

				if (addressOpt.isEmpty()) {
					System.out.println("endereco inválido");
					return;
				}

				Address address = addressOpt.get();
				addressService.createAddress(address);

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
