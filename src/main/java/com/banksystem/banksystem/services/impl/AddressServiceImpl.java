package com.banksystem.banksystem.services.impl;

import com.banksystem.banksystem.models.Address;
import com.banksystem.banksystem.repositories.AddressRepository;
import com.banksystem.banksystem.services.AddressService;

import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.createAddress(address);
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

        if (!cep.matches("\\d{8}"))
            return Optional.empty();

        // validando complemento (address_2)
        if (token.length == 6) {
            if (token[5].length() > 30)
                return Optional.empty();
            else
                complemento = token[5].trim();
        }

        Address addressObj = new Address();
        addressObj.setAddress(rua);
        addressObj.setCep(Integer.parseInt(cep));
        addressObj.setSecondAddress(complemento);
        addressObj.setCity(cidade);
        addressObj.setState(estado);
        addressObj.setHouseNumber(Integer.parseInt(numero));

        return Optional.of(addressObj);
    }

}
