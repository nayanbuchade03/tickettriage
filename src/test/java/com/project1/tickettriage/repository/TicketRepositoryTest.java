package com.project1.tickettriage.repository;

import com.project1.tickettriage.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void shouldSaveAndFetchTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("Login issue");
        ticket.setDescription("User cannot login after password reset.");
        ticket.setStatus("OPEN");
        ticket.setCategory("ACCESS");
        ticket.setPriority("HIGH");

        Ticket saved = ticketRepository.save(ticket);

        assertNotNull(saved.getId());
        assertEquals("ACCESS", saved.getCategory());
        assertEquals("HIGH", saved.getPriority());
    }
}