package com.zeotoni.bank_api.domain.auditoria;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogAuditoriaContaService {

    private final LogAuditoriaContaRepository logAuditoriaContaRepository;

    public LogAuditoriaContaService(LogAuditoriaContaRepository logAuditoriaContaRepository) {
        this.logAuditoriaContaRepository = logAuditoriaContaRepository;
    }

    public List<LogAuditoriaConta> listLogsAuditoria() {
        return logAuditoriaContaRepository.findAll();
    }
}
