package com.project1.tickettriage.repository;

import com.project1.tickettriage.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
