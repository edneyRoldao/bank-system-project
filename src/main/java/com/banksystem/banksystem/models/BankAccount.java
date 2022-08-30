package com.banksystem.banksystem.models;

import com.banksystem.banksystem.config.ApplicationProperties;
import com.banksystem.banksystem.enums.AccountType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount implements Serializable {

    public BankAccount(Long clientId, AccountType accountType) {
        this.clientId = clientId;
        this.accountType = accountType;

        this.accountNumber = clientId + 1000;
        this.registrationDate = LocalDateTime.now();
        this.agency = ApplicationProperties.AGENCY;
    }

    private Long id;
    private Long clientId;
    private Integer agency;
    private Double balance;
    private Long accountNumber;
    private AccountType accountType;
    private LocalDateTime registrationDate;
    private LocalDateTime deactivationDate;

}
