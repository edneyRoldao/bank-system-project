package com.banksystem.banksystem.models;

import com.banksystem.banksystem.enums.Operation;
import com.banksystem.banksystem.enums.TransactionType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {

    private Long id;
    private Double value;
    private Long accountId;
    private Operation operation;
    private TransactionType type;
    private Long transferAccountId;
    private LocalDateTime operationDate;

}
