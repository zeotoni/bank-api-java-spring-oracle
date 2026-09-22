package com.zeotoni.bank_api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleError404(EntityNotFoundException ex) {
        String msg = (ex.getMessage() == null || ex.getMessage().isBlank())
                ? "Recurso não encontrado."
                : ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValidation>> handleError400(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());
    }


    @ExceptionHandler(UncategorizedSQLException.class)
    public ResponseEntity<String> handleProcedureError(UncategorizedSQLException ex) {
        String fullMessage = ex.getCause().getMessage();

        Pattern pattern = Pattern.compile("ORA-\\d+: ([^\n]+)");
        Matcher matcher = pattern.matcher(fullMessage);

        String cleanMessage = matcher.find() ? matcher.group(1).trim() : "Erro ao processar a transferência";

        return ResponseEntity.badRequest().body(cleanMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicate(DataIntegrityViolationException ex) {
        String msg = String.valueOf(ex.getMostSpecificCause().getMessage());

        if (msg.contains("EMAIL")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado.");
        }
        if (msg.contains("CPF")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já cadastrado.");
        }
        if (msg.contains("NUMERO")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Número da conta já existe.");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Registro duplicado.");
    }

    public record DataErrorValidation(String field, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
