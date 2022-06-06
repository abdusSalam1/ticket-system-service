package com.assignment.handler;

import com.assignment.domain.Ticket;
import com.assignment.model.TicketModel;
import com.assignment.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketHandler {

    @Autowired
    private TicketService ticketService;

    public ResponseEntity<List<TicketModel>> getTickets() {
        List<Ticket> deliveryDetails = ticketService.findAll();
        List<TicketModel> ticketModels = deliveryDetails.stream().map(this::toModel).collect(Collectors.toList());
        return ResponseEntity.ok().body(ticketModels);
    }

    private TicketModel toModel(Ticket ticket) {
        return TicketModel.builder()
                .deliveryId(ticket.getDeliveryId())
                .customerType(ticket.getCustomerType())
                .deliveryStatus(ticket.getDeliveryStatus())
                .expectedDeliveryTime(ticket.getExpectedDeliveryTime())
                .timeToReachDestination(ticket.getTimeToReachDestination())
                .build();
    }
}