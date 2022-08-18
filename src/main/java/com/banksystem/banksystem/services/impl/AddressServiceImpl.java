package com.banksystem.banksystem.services.impl;

import com.banksystem.banksystem.models.Address;
import com.banksystem.banksystem.services.AddressService;

import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    @Override
    public void createAddress(Address address) {
        // universidade XTI (110, 111, 112, 113)
    }

    @Override
    public Optional<Address> buildAddress(String address) {
        String[] token = address.split(",");

        if (token.length < 5 || token.length > 6)
            return Optional.empty();

        String complemento = "";
        String rua = token[0].trim();
        String numero = token[1].trim();
        String cidade = token[2].trim();
        String estado = token[3].trim();
        String cep = token[4].trim();

        if (rua.length() < 5 || rua.length() > 30)
            return Optional.empty();

        if (!numero.matches("\\d{1,5}"))
            return Optional.empty();

        if (cidade.length() < 5 || cidade.length() > 30)
            return Optional.empty();

        if (estado.length() != 2)
            return Optional.empty();

        if (cep.length() != 8)
            return Optional.empty();

        // validando complemento (address_2)
        if (token.length == 6 && token[5].length() > 30)
            return Optional.empty();

        Address addressObj = new Address();
        addressObj.setAddress(rua);
        addressObj.setCep(cep);
        addressObj.setSecondAddress(complemento);
        addressObj.setCity(cidade);
        addressObj.setState(estado);
        addressObj.setHouseNumber(Integer.parseInt(numero));

        return Optional.of(addressObj);
    }

}
