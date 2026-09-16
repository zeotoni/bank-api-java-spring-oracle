package com.zeotoni.bank_api.controller;

import com.zeotoni.bank_api.domain.cliente.Cliente;
import com.zeotoni.bank_api.domain.cliente.ClienteDetailsData;
import com.zeotoni.bank_api.domain.cliente.ClienteRegistrationData;
import com.zeotoni.bank_api.domain.cliente.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDetailsData> registerCliente(@RequestBody @Valid ClienteRegistrationData data, UriComponentsBuilder uriBuilder) {

        Cliente cliente = clienteService.registerCliente(data);
        var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDetailsData(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDetailsData>> listClientes() {
        List<ClienteDetailsData> list = clienteService.listClientes()
                .stream()
                .map(ClienteDetailsData::new)
                .toList();

        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetailsData> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(new ClienteDetailsData(cliente));
    }
}
