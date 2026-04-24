package com.project1.tickettriage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter

public class CreateTicketRequest {

    private String title;
    private String description;

}
