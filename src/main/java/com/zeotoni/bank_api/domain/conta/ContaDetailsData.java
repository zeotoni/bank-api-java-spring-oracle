package com.zeotoni.bank_api.domain.conta;

import java.math.BigDecimal;

public record ContaDetailsData(Long id, String numero, BigDecimal saldo, Long clienteId) {
    public ContaDetailsData(Conta conta) {
        this(conta.getId(), conta.getNumero(), conta.getSaldo(), conta.getCliente().getId());
    }
}
