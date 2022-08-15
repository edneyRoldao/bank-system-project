
SELECT	ba.account_number	AS numero_conta,
		ba.agency 			AS agencia,
		c.name 				AS 'nome do cliente'
FROM	bank_account ba INNER JOIN
		client c ON (ba.client_id = c.id);


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


-- <> diferente
SELECT	t.value ,
		t.operation ,
		t.operation_dt ,
		t.transaction_tp
	FROM bank_account b INNER JOIN transactions t ON (t.account_id = b.id)
WHERE b.account_number 	= 	201    			AND
      b.agency 			= 	1				AND
      t.operation		<>	'ACESSO';



-- between (pesquisa por range de data)
SELECT	t.value ,
	t.operation ,
	t.operation_dt ,
	t.transaction_tp
	FROM bank_account b INNER JOIN transactions t ON (t.account_id = b.id)
WHERE	t.operation		<>	'ACESSO'	AND
		t.operation_dt	BETWEEN '2022-06-01' AND '2022-07-30';

-- not between
SELECT	t.value ,
	t.operation ,
	t.operation_dt ,
	t.transaction_tp
	FROM bank_account b INNER JOIN transactions t ON (t.account_id = b.id)
WHERE	t.operation		<>	'ACESSO'	AND
		t.operation_dt	NOT BETWEEN '2022-06-01' AND '2022-07-30';


-- clausula IN
SELECT	t.value ,
		t.operation ,
		t.operation_dt ,
		t.transaction_tp
		FROM bank_account b INNER JOIN transactions t ON (t.account_id = b.id)
WHERE t.transaction_tp IN ('SAQUE', 'DEPOSITO') ORDER BY t.transaction_tp ASC;


-- clausula NOT IN
SELECT	t.value ,
		t.operation ,
		t.operation_dt ,
		t.transaction_tp
		FROM bank_account b INNER JOIN transactions t ON (t.account_id = b.id)
WHERE t.transaction_tp NOT IN ('SAQUE', 'DEPOSITO');


-- count
SELECT count(*) FROM transactions t;


-- clausula limit (limita a quantidade de resultados)
SELECT	t.value ,
		t.operation ,
		t.operation_dt ,
		t.transaction_tp
		FROM bank_account b INNER JOIN transactions t ON (t.account_id = b.id) limit 10;

SELECT * FROM client c;

-- LIKE (qualquer email que termina com a palavra com)
SELECT * FROM client c WHERE c.email LIKE '%com';

-- LIKE (qualquer email que comeca com R)
SELECT * FROM client c WHERE c.email LIKE 'R%';

-- LIKE (busca qualquer termo no banco)
SELECT * FROM client c WHERE c.email LIKE '%na%';


-- maior >
SELECT * FROM bank_account WHERE balance > 1000;

-- menor <
SELECT * FROM bank_account WHERE balance < 1000;

