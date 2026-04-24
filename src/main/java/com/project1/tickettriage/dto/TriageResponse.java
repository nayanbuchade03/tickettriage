package com.project1.tickettriage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TriageResponse {
    private String category;
    private String priority;
    private String suggestedStatus;
    private String reasoning;
}
