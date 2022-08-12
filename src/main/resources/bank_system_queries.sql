
-- Autenticar
SELECT a.password FROM bank_account ba INNER JOIN access a ON (a.account_id = ba.id)
WHERE ba.account_number = :conta AND
      ba.agency         = :agencia;

