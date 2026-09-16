package com.zeotoni.bank_api.domain.transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoDetailsData(Long id, BigDecimal valor, LocalDateTime data, Long contaOrigemId, Long contaDestinoId) {
    public TransacaoDetailsData(Transacao transacao) {
        this(transacao.getId(), transacao.getValor(), transacao.getData(), transacao.getContaOrigem().getId(), transacao.getContaDestino().getId());
    }
}
