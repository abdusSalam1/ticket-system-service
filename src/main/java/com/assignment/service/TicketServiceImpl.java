package com.assignment.service;

import com.assignment.domain.DeliveryDetail;
import com.assignment.domain.Ticket;
import com.assignment.factory.TicketFactory;
import com.assignment.repository.DeliveryDetailRepository;
import com.assignment.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DeliveryDetailRepository deliveryDetailRepository;
    private final TicketFactory ticketFactory;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, DeliveryDetailRepository deliveryDetailRepository,
                             TicketFactory ticketFactory) {
        this.ticketRepository = ticketRepository;
        this.deliveryDetailRepository = deliveryDetailRepository;
        this.ticketFactory = ticketFactory;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void generateTickets() {
        List<DeliveryDetail> deliveryDetails = deliveryDetailRepository.findOrdersToDeliver();
        List<Ticket> tickets = deliveryDetails.stream().map(ticketFactory::create).collect(Collectors.toList());
        this.ticketRepository.saveAll(tickets);
    }
}
