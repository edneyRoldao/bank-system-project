package com.banksystem.banksystem.services.impl;

import com.banksystem.banksystem.services.AddressService;

public class AddressServiceImpl implements AddressService {

    @Override
    public void createAddress(String address) {

    }

    @Override
    public boolean isAddressValid(String address) {
        String[] token = address.split(",");
        System.out.println(token.length);

        if (token.length < 5 || token.length > 6)
            return false;

        return true;
    }

}
