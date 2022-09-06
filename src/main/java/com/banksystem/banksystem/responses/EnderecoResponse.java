package com.banksystem.banksystem.responses;

import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse implements Serializable {

    private String uf;
    private String cep;
    private String bairro;
    private String logradouro;
    private String localidade;

}
