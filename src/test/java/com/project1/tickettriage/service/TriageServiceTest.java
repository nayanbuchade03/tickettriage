package com.project1.tickettriage.service;

import com.project1.tickettriage.dto.TriageRequest;
import com.project1.tickettriage.dto.TriageResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriageServiceTest {

    private final TriageService triageService = new TriageService(new com.project1.tickettriage.provider.RuleBasedTriageProvider());

    @Test
    void shouldClassifyAccessIssueAsHighPriority() {
        TriageRequest request = new TriageRequest();
        request.setTitle("Cannot login to dashboard");
        request.setDescription("User is blocked after password reset and cannot login.");

        TriageResponse response = triageService.analyze(request);

        assertEquals("ACCESS", response.getCategory());
        assertEquals("HIGH", response.getPriority());
        assertEquals("OPEN", response.getSuggestedStatus());
        assertNotNull(response.getReasoning());
    }

    @Test
    void shouldClassifyBillingIssue() {
        TriageRequest request = new TriageRequest();
        request.setTitle("Payment failed for premium plan");
        request.setDescription("Customer tried twice and payment failed at checkout.");

        TriageResponse response = triageService.analyze(request);

        assertEquals("BILLING", response.getCategory());
        assertEquals("HIGH", response.getPriority());
    }
}