
-- Autenticar
SELECT	c.name 				,
		ba.balance 			,
		ba.account_tp 		,
		ba.deactivation_dt 	,
		a.password 			,
		a.client_block
	FROM bank_account ba	INNER JOIN client c ON (c.id = ba.client_id)
							INNER JOIN access a ON (a.account_id = ba.id)
WHERE ba.account_number = :conta AND
      ba.agency         = :agencia;

-- AtualizarSaldo (faz update do saldo na tabela bank_account pelo numero da conta e numero da agencia)
UPDATE bank_account ba
    SET ba.balance = :saldo
WHERE ba.account_number = :conta    AND
      ba.agency         = :agencia;

-- InativarConta
UPDATE bank_account ba
    SET ba.deactivation_dt = DATETIME()
WHERE ba.account_number = :conta    AND
      ba.agency         = :agencia;
