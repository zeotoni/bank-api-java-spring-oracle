CREATE OR REPLACE FUNCTION saldo_suficiente(conta_id IN NUMBER, valor IN NUMBER)
RETURN VARCHAR2
IS
    v_saldo CONTAS.SALDO%TYPE;
BEGIN
    SELECT c.SALDO
    INTO v_saldo
    FROM CONTAS c
    WHERE c.ID = conta_id;

    IF v_saldo >= valor THEN
        RETURN 'Y';
    ELSE
        RETURN 'N';
    END IF;

    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20005, 'Conta não encontrada');
    WHEN OTHERS THEN
        RAISE;
END;
/