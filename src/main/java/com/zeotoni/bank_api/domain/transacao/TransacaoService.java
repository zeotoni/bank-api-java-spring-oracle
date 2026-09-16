package com.zeotoni.bank_api.domain.transacao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;
    private final JdbcTemplate jdbcTemplate;

    public TransacaoService(TransacaoRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public String registerTransacao(TransacaoRegistrationData data) {
        String query = "{CALL transferir(?,?,?)}";

        jdbcTemplate.update(
            query,
            data.valor(),
            data.contaOrigemId(),
            data.contaDestinoId()
        );

        return "Transferência realizada com sucesso!";
    }

    public List<Transacao> listTransacoes() {
        return repository.findAll();
    }

    public List<Transacao> listByConta(Long contaId) {
        return repository.findByContaDestino_IdOrContaOrigem_Id(contaId, contaId);
    }

    public Optional<Transacao> findById(Long id) {
        return repository.findById(id);
    }
}
