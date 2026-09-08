package com.zeotoni.bank_api.domain.cliente;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente registerCliente(ClienteRegistrationData data) {
        Cliente cliente = new Cliente(data);
        return repository.save(cliente);
    }

    public List<Cliente> listClientes() {
        return repository.findAll();
    }

    public Cliente getClienteById(Long cliente_id) {
        return repository.findById(cliente_id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }
}
