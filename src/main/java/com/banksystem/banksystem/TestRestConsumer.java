package com.banksystem.banksystem;

import com.banksystem.banksystem.clients.ViaCepClient;
import com.banksystem.banksystem.clients.impl.ViaCepClientImpl;
import com.banksystem.banksystem.responses.EnderecoResponse;

public class TestRestConsumer {

    public static void main(String[] args) {
        ViaCepClient client = new ViaCepClientImpl();

        EnderecoResponse response = client.getEnderecoByCep("02343030")
                .orElseThrow(() -> new RuntimeException("end nao encontrado"));

        System.out.println(response);
    }

}
