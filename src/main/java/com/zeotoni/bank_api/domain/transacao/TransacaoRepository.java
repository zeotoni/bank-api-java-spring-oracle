package com.zeotoni.bank_api.domain.transacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByContaDestino_IdOrContaOrigem_Id(Long contaDestinoId, Long contaOrigemId);
}
