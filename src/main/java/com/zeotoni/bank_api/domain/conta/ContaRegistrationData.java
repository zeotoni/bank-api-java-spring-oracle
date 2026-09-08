package com.zeotoni.bank_api.domain.conta;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ContaRegistrationData(

        @NotBlank(message = "O número da conta não pode estar vazio")
        @Pattern(regexp = "^\\d{6,10}(-\\d)?$", message = "Número de conta inválido. Use entre 6 e 10 dígitos, com dígito verificador opcional (ex: 1234567-8)")
        String numero,

        @NotNull(message = "O saldo não pode estar vazio")
        BigDecimal saldo,

        @NotNull(message = "O cliente não pode estar vazio")
        Long clienteId
) {
}
