package com.project1.tickettriage.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class TriageRequest {
    private String title;
    private String description;
}
