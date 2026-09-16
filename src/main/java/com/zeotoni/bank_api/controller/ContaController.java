package com.zeotoni.bank_api.controller;

import com.zeotoni.bank_api.domain.conta.Conta;
import com.zeotoni.bank_api.domain.conta.ContaDetailsData;
import com.zeotoni.bank_api.domain.conta.ContaRegistrationData;
import com.zeotoni.bank_api.domain.conta.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaDetailsData> registerConta(@RequestBody @Valid ContaRegistrationData data, UriComponentsBuilder uriBuilder) {

        Conta conta = contaService.registerConta(data);
        var uri = uriBuilder.path("/contas/{id}").buildAndExpand(conta.getId()).toUri();

        return ResponseEntity.created(uri).body(new ContaDetailsData(conta));
    }

    @GetMapping
    public ResponseEntity<List<ContaDetailsData>> listContas() {
        List<ContaDetailsData> list = contaService.listContas()
                .stream()
                .map(ContaDetailsData::new)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDetailsData> getContaById(@PathVariable Long id) {
        Conta conta = contaService.getContaById(id);
        return ResponseEntity.ok(new ContaDetailsData(conta));
    }
}
