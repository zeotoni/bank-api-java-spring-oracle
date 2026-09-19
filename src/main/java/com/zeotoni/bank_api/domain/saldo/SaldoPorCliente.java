package com.zeotoni.bank_api.domain.saldo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity(name = "SaldoPorCliente")
@Table(name = "view_saldo_por_cliente")
@Immutable
@NoArgsConstructor
@Getter
public class SaldoPorCliente {

    @Id
    @Column(name = "CLIENTE_ID", nullable = false)
    private Long clienteId;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "NUMERO_DE_CONTAS", nullable = false)
    private Integer numeroDeContas;

    @Column(name = "SALDO_TOTAL", nullable = false)
    private BigDecimal saldoTotal;
}
