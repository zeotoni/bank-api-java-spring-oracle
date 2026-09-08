CREATE TABLE contas (
    id NUMBER GENERATED ALWAYS AS IDENTITY,
    cliente_id NUMBER NOT NULL,
    numero VARCHAR2(100) NOT NULL UNIQUE,
    saldo NUMBER(15,2) DEFAULT 0 NOT NULL,
    CONSTRAINT contas_pk PRIMARY KEY (id),
    CONSTRAINT fk_contas_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);