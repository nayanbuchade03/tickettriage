package com.project1.tickettriage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateTicketStatusRequest {

    @NotBlank
    private String status;
}