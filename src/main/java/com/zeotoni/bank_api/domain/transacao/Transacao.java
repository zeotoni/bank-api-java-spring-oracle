package com.zeotoni.bank_api.domain.transacao;

import com.zeotoni.bank_api.domain.conta.Conta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Transacao")
@Table(name = "transacoes")
@NoArgsConstructor
@Getter
@Setter
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_transacao", nullable = false)
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoTransacao tipo;

    @ManyToOne
    @JoinColumn(name = "conta_origem_id")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino_id")
    private Conta contaDestino;

    public Transacao(BigDecimal valor, Conta contaOrigem, Conta contaDestino, TipoTransacao tipo) {
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.tipo = tipo;
    }
}
