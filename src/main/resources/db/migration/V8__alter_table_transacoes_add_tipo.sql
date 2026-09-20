ALTER TABLE transacoes ADD
    tipo VARCHAR2(20) NOT NULL;

ALTER TABLE transacoes ADD
    CONSTRAINT ck_transacoes_tipo CHECK (tipo IN ('TRANSFERENCIA', 'SAQUE', 'DEPOSITO'));

ALTER TABLE transacoes MODIFY conta_origem_id NULL;

ALTER TABLE transacoes MODIFY conta_destino_id NULL;
