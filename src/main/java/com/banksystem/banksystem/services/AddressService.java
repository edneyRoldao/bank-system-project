package com.banksystem.banksystem.services;

public interface AddressService {

    void createAddress(String address);

    boolean isAddressValid(String address);

}
