package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.models.Address;

public interface AddressRepository {

    /**
     * @param address dados do endereco que serao salvos no banco de dados
     * @return retorno o id que foi atribuido ao endereco criado
     */
    Long insertAddress(Address address);

    Address getAddressById(Long id);

}
