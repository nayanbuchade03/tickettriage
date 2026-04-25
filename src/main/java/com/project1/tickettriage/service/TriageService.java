package com.project1.tickettriage.service;

import com.project1.tickettriage.dto.TriageRequest;
import com.project1.tickettriage.dto.TriageResponse;
import com.project1.tickettriage.provider.AiTriageProvider;
import org.springframework.stereotype.Service;

@Service
public class TriageService {

    private final AiTriageProvider aiTriageProvider;

    public TriageService(AiTriageProvider aiTriageProvider) {
        this.aiTriageProvider = aiTriageProvider;
    }

    public TriageResponse analyze(TriageRequest request) {
        return aiTriageProvider.analyze(request);
    }
}