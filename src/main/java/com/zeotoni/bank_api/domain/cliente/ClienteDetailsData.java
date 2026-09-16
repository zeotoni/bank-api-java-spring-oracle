package com.zeotoni.bank_api.domain.cliente;

public record ClienteDetailsData(Long id, String nome, String email, String cpf) {
    public ClienteDetailsData(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(),cliente.getCpf());
    }
}
