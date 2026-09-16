package com.zeotoni.bank_api.domain.conta;

import com.zeotoni.bank_api.domain.cliente.Cliente;
import com.zeotoni.bank_api.domain.cliente.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository repository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Conta registerConta(ContaRegistrationData data) {
        Cliente cliente = clienteRepository.findById(data.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Conta conta = new Conta(data.numero(), data.saldo(), cliente);
        return repository.save(conta);
    }

    public List<Conta> listContas() {
        return repository.findAll();
    }

    public List<Conta> listContaByClienteId(Long id) {
        return repository.findContaByCliente_Id(id);
    }

    public Optional<Conta> findByNumero(String numero) {
        return repository.findContaByNumero(numero);
    }

    public Conta getContaById(Long conta_id) {
        return repository.findById(conta_id)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
    }
}
