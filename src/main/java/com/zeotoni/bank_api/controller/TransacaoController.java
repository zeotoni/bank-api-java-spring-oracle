package com.zeotoni.bank_api.controller;

import com.zeotoni.bank_api.domain.conta.Conta;
import com.zeotoni.bank_api.domain.conta.ContaDetailsData;
import com.zeotoni.bank_api.domain.conta.ContaRegistrationData;
import com.zeotoni.bank_api.domain.transacao.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/transferencia")
    public ResponseEntity<String> transferir(@RequestBody @Valid TransferenciaRegistrationData data) {

        String transacao = transacaoService.registerTransferencia(data);

        return ResponseEntity.ok(transacao);
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> depositar(@RequestBody @Valid DepositoRegistrationData data) {

        String deposito = transacaoService.depositar(data);

        return ResponseEntity.ok(deposito);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDetailsData>> listTransacoes() {
        List<TransacaoDetailsData> list = transacaoService.listTransacoes()
                .stream()
                .map(TransacaoDetailsData::new)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDetailsData> getTransacaoById(@PathVariable Long id) {
        Transacao transacao = transacaoService.findById(id);
        return ResponseEntity.ok(new TransacaoDetailsData(transacao));
    }
}
