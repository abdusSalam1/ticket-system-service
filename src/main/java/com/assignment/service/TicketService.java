package com.assignment.service;

import com.assignment.domain.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    void generateTickets();
}
