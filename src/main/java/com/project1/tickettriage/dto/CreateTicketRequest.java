package com.project1.tickettriage.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter

public class CreateTicketRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title must be 1–100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 5000, message = "Description must be 10–5000 characters")
    private String description;

}
