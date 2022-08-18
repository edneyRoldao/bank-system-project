package com.banksystem.banksystem.services;

import com.banksystem.banksystem.models.Address;

import java.util.Optional;

public interface AddressService {

    void createAddress(Address address);

    Optional<Address> buildAddress(String address);

}
