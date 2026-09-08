package com.zeotoni.bank_api.domain.conta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findContaByNumero(String numero);

    List<Conta> findContaByCliente_Id(Long clientId);
}
