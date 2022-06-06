package com.assignment.generator;

import com.assignment.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TicketGenerator {

    @Autowired
    private TicketService ticketService;

    @Scheduled(cron = "${cron.expression.generate.tickets}")
    public void generate() {
        ticketService.generateTickets();
    }
}
