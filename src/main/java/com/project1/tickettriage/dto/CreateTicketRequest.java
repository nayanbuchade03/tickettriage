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

    @NotBlank(message = "title is required")
    @Size(min = 5, max = 100, message = "title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "description is required")
    @Size(min = 10, max = 1000, message = "description must be between 10 and 1000 characters")
    private String description;

}
