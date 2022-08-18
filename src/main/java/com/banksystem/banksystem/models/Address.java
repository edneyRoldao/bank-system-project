package com.banksystem.banksystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private Long id;
    private String cep;
    private String city;
    private String state;
    private String address;
    private Integer houseNumber;
    private String secondAddress;

}
