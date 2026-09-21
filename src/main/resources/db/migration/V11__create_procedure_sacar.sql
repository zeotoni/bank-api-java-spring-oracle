CREATE OR REPLACE PROCEDURE sacar (valor IN NUMBER, conta_id IN NUMBER)
IS
    v_saldo NUMBER;
    resultado VARCHAR2(1);
BEGIN

    IF valor <= 0 THEN
        RAISE_APPLICATION_ERROR(-20006, 'O valor de saque precisa ser maior que 0!');
    END IF;

    SELECT saldo INTO v_saldo FROM contas WHERE contas.id = conta_id FOR UPDATE;

    resultado := saldo_suficiente(conta_id, valor);

    IF resultado = 'Y' THEN
        UPDATE contas SET saldo = saldo - valor WHERE id = conta_id;
        INSERT INTO transacoes (valor, conta_origem_id, tipo)
        VALUES (valor, conta_id, 'SAQUE');
    ELSE
        RAISE_APPLICATION_ERROR(-20007, 'O saldo disponível é insuficiente!');
    END IF;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20008, 'Conta não encontrada');
    WHEN OTHERS THEN
        RAISE;
END;
/