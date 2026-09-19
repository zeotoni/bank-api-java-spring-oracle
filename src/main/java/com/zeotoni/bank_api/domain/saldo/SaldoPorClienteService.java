package com.zeotoni.bank_api.domain.saldo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaldoPorClienteService {

    private final SaldoPorClienteRepository saldoPorClienteRepository;

    public SaldoPorClienteService(SaldoPorClienteRepository saldoPorClienteRepository) {
        this.saldoPorClienteRepository = saldoPorClienteRepository;
    }

    public List<SaldoPorCliente> listSaldoPorCliente() {
        return saldoPorClienteRepository.findAll();
    }
}
