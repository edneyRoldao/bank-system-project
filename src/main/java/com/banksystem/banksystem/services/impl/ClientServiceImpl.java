package com.banksystem.banksystem.services.impl;

import com.banksystem.banksystem.enums.DocumentType;
import com.banksystem.banksystem.enums.PersonType;
import com.banksystem.banksystem.models.Client;
import com.banksystem.banksystem.services.ClientService;
import com.banksystem.banksystem.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    @Override
    public Optional<Client> validateAndBuildClient(String clientAsString) {
        String[] token = clientAsString.split(",");

        if (token.length != 5)
            return Optional.empty();

        String nome = token[0].trim();
        String email = token[1].trim();
        String telefone = token[2].trim();
        String documento = token[3].trim();
        String dtNascimento = token[4].trim();

        if (StringUtils.isBlank(nome))
            return Optional.empty();

        if (StringUtils.isBlank(email) || !email.matches("^(.+)@(.+)$"))
            return Optional.empty();

        if (StringUtils.isBlank(telefone) || !telefone.matches("\\d{11}"))
            return Optional.empty();

        if (StringUtils.isBlank(documento) || !documento.matches("(\\d{11})|(\\d{14})"))
            return Optional.empty();

        LocalDate dataNascimento;
        if (StringUtils.isBlank(dtNascimento)) {
            return Optional.empty();
        } else {
            try {
                dataNascimento = DateUtil.stringToLocalDate(dtNascimento, "dd/MM/yyyy");

            } catch (Exception e) {
                return Optional.empty();
            }
        }

        Client client = Client
                .builder()
                .name(nome)
                .email(email)
                .document(documento)
                .personType(PersonType.PF)
                .birthdate(dataNascimento)
                .documentType(DocumentType.CPF)
                .phone(Integer.parseInt(telefone))
                .build();

        return Optional.of(client);
    }

    @Override
    public Client createClient(Client client) {
        return null;
    }

}
