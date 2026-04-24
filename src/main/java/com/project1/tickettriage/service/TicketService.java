package com.project1.tickettriage.service;

import com.project1.tickettriage.dto.CreateTicketRequest;
import com.project1.tickettriage.dto.TriageRequest;
import com.project1.tickettriage.dto.TriageResponse;
import com.project1.tickettriage.entity.Ticket;
import com.project1.tickettriage.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
}
