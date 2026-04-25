package com.project1.tickettriage.controller;

import com.project1.tickettriage.dto.CreateTicketRequest;
import com.project1.tickettriage.dto.UpdateTicketStatusRequest;
import com.project1.tickettriage.entity.Ticket;
import com.project1.tickettriage.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@RequestBody CreateTicketRequest request){
        return ticketService.createTicket(request);
    }

    @PostMapping
    public Ticket createTicket(@Valid @RequestBody CreateTicketRequest request){
        return ticketService.createTicket(request);
    }

    @PatchMapping("/{id}/status")
    public Ticket updateTicketStatus(@PathVariable Long id, @Valid @RequestBody UpdateTicketStatusRequest request) {
        return ticketService.updateTicketStatus(id, request);
    }
}
