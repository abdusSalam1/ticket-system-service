package com.assignment.factory;

import com.assignment.domain.DeliveryDetail;
import com.assignment.domain.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TicketFactoryTest {

    private TicketFactory ticketFactory;

    @BeforeEach
    public void setup() {
        ticketFactory = new TicketFactory();
    }

    @Test
    public void whenVipDetailShouldCreateHighPriorityTicket() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "vip", "Order Preparing", now, 1, now,2,1);
        //when
        Ticket ticket = ticketFactory.create(deliveryDetail);
        //than
        assertEquals(1, ticket.getPriority());
    }

    @Test
    public void whenExpectedTimePassedShouldCreateHighPriorityTicket() {
        //given
        LocalDateTime expectedDelivery = LocalDateTime.of(2030,12,1,00,00);
        LocalDateTime timeToReach = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "loyal", "Order Preparing", expectedDelivery, 1, timeToReach,2,1);
        //when
        Ticket ticket = ticketFactory.create(deliveryDetail);
        //than
        assertEquals(1, ticket.getPriority());
    }

    @Test
    public void whenLoyalCustomerDetailShouldCreateMediumPriorityTicket() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "loyal", "Order Preparing", now, 1, now,2,1);
        //when
        Ticket ticket = ticketFactory.create(deliveryDetail);
        //than
        assertEquals(2, ticket.getPriority());
    }

    @Test
    public void whenNewCustomerDetailShouldCreateMediumPriorityTicket() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "new", "Order Preparing", now, 1, now,2,1);
        //when
        Ticket ticket = ticketFactory.create(deliveryDetail);
        //than
        assertEquals(3, ticket.getPriority());
    }


}