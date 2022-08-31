package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.Address;

public interface AddressRepository extends Repository {

    String INSERT_ADDRESS_SQL = "INSERT INTO address (city, state, address, house_number, cep, address_2) VALUES (?,?,?,?,?,?)";

    /**
     * @param address dados do endereco que serao salvos no banco de dados
     * @return retorno o id que foi atribuido ao endereco criado
     */
    Address createAddress(Address address);

}
