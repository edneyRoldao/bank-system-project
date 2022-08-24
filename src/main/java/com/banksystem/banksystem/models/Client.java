package com.banksystem.banksystem.models;

import com.banksystem.banksystem.enums.DocumentType;
import com.banksystem.banksystem.enums.PersonType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    private Long id;
    private String name;
    private String email;
    private Integer phone;
    private Long addressId;
    private String document;
    private LocalDate birthdate;
    private PersonType personType;
    private DocumentType documentType;

}
