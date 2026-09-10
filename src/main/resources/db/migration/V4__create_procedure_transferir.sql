CREATE OR REPLACE PROCEDURE transferir (valor IN NUMBER, conta_origemID IN NUMBER, conta_destinoID IN NUMBER)
IS
    v_saldo_origem NUMBER;
    v_saldo_destino NUMBER;
BEGIN
    IF conta_origemID = conta_destinoID THEN
       RAISE_APPLICATION_ERROR(-20001, 'Os números precisam ser de contas diferentes!');
    END IF;

    IF valor <= 0 THEN
       RAISE_APPLICATION_ERROR(-20002, 'O valor de transferência precisa ser maior que 0!');
    END IF;


    SELECT saldo INTO  v_saldo_origem FROM contas WHERE contas.id = conta_origemID FOR UPDATE;
    SELECT saldo INTO  v_saldo_destino FROM contas WHERE contas.id = conta_destinoID;

    IF v_saldo_origem >= valor THEN
        UPDATE contas SET saldo = saldo - valor WHERE id = conta_origemID;
        UPDATE contas SET saldo = saldo + valor WHERE id = conta_destinoID;
        INSERT INTO transacoes (valor, conta_origem_id, conta_destino_id)
        VALUES (valor, conta_origemID, conta_destinoID);
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'O saldo disponível é insuficiente!');
    END IF;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20004, 'Conta não encontrada');
    WHEN OTHERS THEN
        RAISE;
END;
/