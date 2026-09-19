package com.zeotoni.bank_api.controller;

import com.zeotoni.bank_api.domain.saldo.SaldoPorClienteDetailsData;
import com.zeotoni.bank_api.domain.saldo.SaldoPorClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes/saldo-total")
public class SaldoPorClienteController {

    private final SaldoPorClienteService saldoPorClienteService;

    public SaldoPorClienteController(SaldoPorClienteService saldoPorClienteService) {
        this.saldoPorClienteService = saldoPorClienteService;
    }

    @GetMapping
    public ResponseEntity<List<SaldoPorClienteDetailsData>> listSaldoPorCliente() {
        List<SaldoPorClienteDetailsData> list = saldoPorClienteService.listSaldoPorCliente()
                .stream()
                .map(SaldoPorClienteDetailsData::new)
                .toList();

        return ResponseEntity.ok(list);
    }
}
