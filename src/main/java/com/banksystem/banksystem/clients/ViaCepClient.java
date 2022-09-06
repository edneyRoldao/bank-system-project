package com.banksystem.banksystem.clients;

import com.banksystem.banksystem.responses.EnderecoResponse;

import java.util.Optional;

public interface ViaCepClient {

    Optional<EnderecoResponse> getEnderecoByCep(String cep);

}
