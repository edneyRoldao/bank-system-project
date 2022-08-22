package com.banksystem.banksystem.services;

import com.banksystem.banksystem.models.Address;

import java.util.Optional;

public interface AddressService {

    Address createAddress(Address address);

    Optional<Address> buildAddress(String address);

}
