package com.zeotoni.bank_api.domain.transacao;

import com.zeotoni.bank_api.domain.conta.Conta;
import com.zeotoni.bank_api.domain.conta.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;
    private final JdbcTemplate jdbcTemplate;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository, JdbcTemplate jdbcTemplate) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public String registerTransferencia(TransferenciaRegistrationData data) {
        String query = "{CALL transferir(?,?,?)}";

        jdbcTemplate.update(
            query,
            data.valor(),
            data.contaOrigemId(),
            data.contaDestinoId()
        );

        return "Transferência realizada com sucesso!";
    }

    @Transactional
    public String depositar(DepositoRegistrationData data) {
        Conta conta = contaRepository.findById(data.contaDestinoId())
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        conta.setSaldo(conta.getSaldo().add(data.valor()));
        contaRepository.save(conta);

        Transacao transacao = new Transacao(data.valor(), null, conta, TipoTransacao.DEPOSITO);
        transacao.setData(LocalDateTime.now());
        transacaoRepository.save(transacao);

        return "Depósito realizado com sucesso!";
    }

    public List<Transacao> listTransacoes() {
        return transacaoRepository.findAll();
    }

    public List<Transacao> listByConta(Long contaId) {
        return transacaoRepository.findByContaDestino_IdOrContaOrigem_Id(contaId, contaId);
    }

    public Transacao findById(Long id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
    }
}
