package com.zeotoni.bank_api.domain.auditoria;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LogAuditoriaContaDetailsData(Long id, Long contaId, BigDecimal saldoAnterior, BigDecimal saldoNovo, LocalDateTime dataAlteracao) {
    public LogAuditoriaContaDetailsData(LogAuditoriaConta logAuditoriaConta) {
        this(logAuditoriaConta.getId(), logAuditoriaConta.getContaId(), logAuditoriaConta.getSaldoAnterior(), logAuditoriaConta.getSaldoNovo(), logAuditoriaConta.getDataAlteracao());
    }
}
