package com.project1.tickettriage.service;

import com.project1.tickettriage.dto.CreateTicketRequest;
import com.project1.tickettriage.dto.TriageRequest;
import com.project1.tickettriage.dto.TriageResponse;
import com.project1.tickettriage.dto.UpdateTicketStatusRequest;
import com.project1.tickettriage.entity.Ticket;
import com.project1.tickettriage.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TriageService triageService;

    public TicketService(TicketRepository ticketRepository, TriageService triageService){
        this.ticketRepository=ticketRepository;
        this.triageService = triageService;
    }

    public Ticket createTicket(CreateTicketRequest request){
        TriageRequest triageRequest=new TriageRequest();
        triageRequest.setTitle(request.getTitle());
        triageRequest.setDescription(request.getDescription());

        TriageResponse triageResponse=triageService.analyze(triageRequest);

        Ticket ticket=new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(triageResponse.getSuggestedStatus());
        ticket.setCategory(triageResponse.getCategory());
        ticket.setPriority(triageResponse.getPriority());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));
    }

    public Ticket updateTicketStatus(Long id, UpdateTicketStatusRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));

        ticket.setStatus(request.getStatus());
        return ticketRepository.save(ticket);
    }
}
