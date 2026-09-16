package com.zeotoni.bank_api.domain.transacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransacaoRegistrationData(

        @NotNull(message = "O valor não pode estar vazio.")
        @Positive(message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        @NotNull(message = "A conta de origem não pode estar vazia.")
        Long contaOrigemId,

        @NotNull(message = "A conta de destino não pode estar vazia.")
        Long contaDestinoId
) {
}
