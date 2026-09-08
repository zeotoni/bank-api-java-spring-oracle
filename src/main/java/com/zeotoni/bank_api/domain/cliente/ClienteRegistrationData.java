package com.zeotoni.bank_api.domain.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRegistrationData(

        @NotBlank(message = "Nome não pode estar vazio")
        @Size(min = 3, message = "O nome precisa ter pelo menos 3 letras")
        String nome,

        @NotBlank(message = "Email não pode estar vazio")
        @Email(message = "Invalido formato de email")
        String email,

        @NotBlank(message = "O cpf não pode estar vazio")
        @Pattern(regexp = "^[0-9]{11}$", message = "Invalido formato de CPF. O CPF precisa ter 11 dígitos")
        String cpf
) {
}
