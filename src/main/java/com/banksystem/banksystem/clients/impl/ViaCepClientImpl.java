package com.banksystem.banksystem.clients.impl;

import com.banksystem.banksystem.clients.ViaCepClient;
import com.banksystem.banksystem.responses.EnderecoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class ViaCepClientImpl implements ViaCepClient {

    @Override
    public Optional<EnderecoResponse> getEnderecoByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = String.format("https://viacep.com.br/ws/%s/json/", cep);
        ResponseEntity<EnderecoResponse> response = restTemplate.getForEntity(URL, EnderecoResponse.class);

        if (response.getBody() != null && response.getStatusCode() == HttpStatus.OK) {
            return Optional.of(response.getBody());
        }

        return Optional.empty();
    }

}
