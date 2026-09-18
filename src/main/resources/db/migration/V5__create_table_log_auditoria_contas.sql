CREATE TABLE log_auditoria_contas (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    conta_id NUMBER NOT NULL,
    saldo_anterior NUMBER(15,2) DEFAULT 0 NOT NULL,
    saldo_novo NUMBER(15,2) DEFAULT 0 NOT NULL,
    data_alteracao TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    CONSTRAINT log_auditoria_contas_pk PRIMARY KEY (id),
    CONSTRAINT fk_log_auditoria_contas_conta FOREIGN KEY (conta_id) REFERENCES contas(id)
);