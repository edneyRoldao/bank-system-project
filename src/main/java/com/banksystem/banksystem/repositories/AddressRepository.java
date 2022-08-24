package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.Address;

public interface AddressRepository {

    String INSERT_ADDRESS_SQL = "INSERT INTO address (city, state, address, house_number, cep, address_2) VALUES (?,?,?,?,?,?)";
    String NEXT_ADDRESS_ID_SQL = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = 'address'";

    /**
     * @param address dados do endereco que serao salvos no banco de dados
     * @return retorno o id que foi atribuido ao endereco criado
     */
    Address createAddress(Address address);

}
