package com.project1.tickettriage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class TriageResponse {
    private String category;
    private String priority;
    private String suggestedStatus;
    private String reasoning;

}
