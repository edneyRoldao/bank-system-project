-- ADDRESS
INSERT INTO address (city, state, address, house_number, cep, address_2)
VALUES ('Sao Paulo', 'SP', 'Rua Abura', 'Imirim', 641, 02542110, 'casa 2');

-- CLIENT
INSERT INTO client (name, birthdate, phone, email, document, address_id)
VALUES ('Erik Gonzaga', '1987-11-06', 11961226929, 'erikgonzaga@email.com', 36141051804, 1);

-- ACCOUNT
INSERT INTO bank_account (account_number, agency, client_id, balance, registration_dt, account_tp)
VALUES (1000, 10, 1, 150.00, '2022-08-03 12:59:59', 'CORRENTE');

-- ACCESS
INSERT INTO access (client_id, account_id, password)
VALUES (1,1, '123456');

-- TRANSACTION
INSERT INTO transactions (value, operation, operation_dt, account_id, transaction_tp)
VALUES (100.00, 'ENTRADA', '2022-08-03 17:30:00', 1, 'DEPOSITO');
