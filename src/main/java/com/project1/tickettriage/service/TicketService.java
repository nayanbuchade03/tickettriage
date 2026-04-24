package com.project1.tickettriage.service;

import com.project1.tickettriage.entity.Ticket;
import com.project1.tickettriage.repository.TicketRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository=ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        ticket.setStatus("OPEN");
        ticket.setCreatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id){
        return ticketRepository.findById(id);
    }
}
