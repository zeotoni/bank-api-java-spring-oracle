package com.zeotoni.bank_api.controller;

import com.zeotoni.bank_api.domain.auditoria.LogAuditoriaContaDetailsData;
import com.zeotoni.bank_api.domain.auditoria.LogAuditoriaContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auditoria")
public class LogAuditoriaContaController {

    private final LogAuditoriaContaService logAuditoriaContaService;

    public LogAuditoriaContaController(LogAuditoriaContaService logAuditoriaContaService) {
        this.logAuditoriaContaService = logAuditoriaContaService;
    }

    @GetMapping
    public ResponseEntity<List<LogAuditoriaContaDetailsData>> listLogs() {
        List<LogAuditoriaContaDetailsData> list = logAuditoriaContaService.listLogsAuditoria()
                .stream()
                .map(LogAuditoriaContaDetailsData::new)
                .toList();

        return ResponseEntity.ok(list);
    }
}
