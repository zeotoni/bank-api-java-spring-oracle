CREATE TABLE transacoes (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    valor NUMBER(15,2) NOT NULL,
    conta_origem_id NUMBER NOT NULL,
    conta_destino_id NUMBER NOT NULL,
    data_transacao TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT transacoes_pk PRIMARY KEY (id),
    CONSTRAINT fk_transacoes_conta_origem FOREIGN KEY (conta_origem_id) REFERENCES contas(id),
    CONSTRAINT fk_transacoes_conta_destino FOREIGN KEY (conta_destino_id) REFERENCES contas(id)
);