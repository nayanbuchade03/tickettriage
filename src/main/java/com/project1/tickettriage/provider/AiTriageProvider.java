package com.project1.tickettriage.provider;

import com.project1.tickettriage.dto.TriageRequest;
import com.project1.tickettriage.dto.TriageResponse;

public interface AiTriageProvider {
    TriageResponse analyze(TriageRequest request);
}
