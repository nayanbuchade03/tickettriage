package com.project1.tickettriage.service;

import com.project1.tickettriage.dto.TriageRequest;
import com.project1.tickettriage.dto.TriageResponse;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class TriageService {
    public TriageResponse analyze(TriageRequest request){
        String combinedText=((request.getTitle()==null?"": request.getTitle())+" "
                +(request.getDescription()==null?"": request.getDescription())).toLowerCase();
        String category="GENERAL";
        String priority="MEDIUM";
        String suggestedStatus="OPEN";
        String reasoning="Matched default triage rule.";

        if (containsAny(combinedText, "payment","invoice","refund","billing","charge")){
            category="BILLING";
            reasoning="Detected billing-related keywords.";
        }
        if(containsAny(combinedText, "login","password","sign in","signin", "otp","account locked")){
            category = "ACCESS";
            reasoning = "Detected access-related keywords.";
        }
        if (containsAny(combinedText, "bug", "error", "exception", "500", "crash", "stacktrace")) {
            category = "TECHNICAL";
            reasoning = "Detected technical issue keywords.";
        }

        if (containsAny(combinedText, "feature request", "enhancement", "improvement")) {
            category = "FEATURE_REQUEST";
            priority = "LOW";
            reasoning = "Detected feature request keywords.";
        }

        if (containsAny(combinedText, "production down", "critical", "urgent", "sev1", "data loss")) {
            priority = "HIGH";
            category = "INCIDENT";
            suggestedStatus = "ESCALATED";
            reasoning = "Detected critical incident keywords.";
        } else if (containsAny(combinedText, "cannot login", "unable to login", "blocked", "failed payment")) {
            priority = "HIGH";
            reasoning = "Detected user-blocking issue keywords.";
        }

        return new TriageResponse(category, priority, suggestedStatus, reasoning);
    }

    private boolean containsAny(String text, String... keywords) {
        for (String keyword : keywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
