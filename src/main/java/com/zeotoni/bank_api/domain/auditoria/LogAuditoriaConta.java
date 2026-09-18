package com.zeotoni.bank_api.domain.auditoria;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "LogAuditoriaConta")
@Table(name = "log_auditoria_contas")
@NoArgsConstructor
@Getter
@Setter
public class LogAuditoriaConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conta_id", nullable = false)
    private Long contaId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoAnterior;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoNovo;

    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;
}
