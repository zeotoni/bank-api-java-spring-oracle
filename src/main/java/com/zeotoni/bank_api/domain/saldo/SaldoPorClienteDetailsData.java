package com.zeotoni.bank_api.domain.saldo;

import java.math.BigDecimal;

public record SaldoPorClienteDetailsData(Long clienteId, String nome, Integer numeroDeContas, BigDecimal saldoTotal) {
    public SaldoPorClienteDetailsData(SaldoPorCliente saldoPorCliente) {
        this(saldoPorCliente.getClienteId(), saldoPorCliente.getNome(), saldoPorCliente.getNumeroDeContas(), saldoPorCliente.getSaldoTotal());
    }
}
