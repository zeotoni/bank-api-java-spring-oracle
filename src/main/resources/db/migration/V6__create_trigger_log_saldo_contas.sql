CREATE OR REPLACE TRIGGER trg_log_saldo_contas
AFTER UPDATE ON contas
FOR EACH ROW
WHEN (OLD.saldo != NEW.saldo)
BEGIN
    INSERT INTO log_auditoria_contas (conta_id, saldo_anterior, saldo_novo)
    VALUES (:NEW.id, :OLD.saldo, :NEW.saldo);
END;
/