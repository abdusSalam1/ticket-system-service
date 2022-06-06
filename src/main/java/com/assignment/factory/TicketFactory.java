package com.assignment.factory;

import com.assignment.domain.DeliveryDetail;
import com.assignment.domain.Ticket;
import com.assignment.helper.Priority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TicketFactory {

    public Ticket create(DeliveryDetail deliveryDetail) {
        if (deliveryDetail.isHighPriorityOrder())
            return createNewTicket(deliveryDetail, Priority.HIGH);
        else if (deliveryDetail.isMediumPriority())
            return createNewTicket(deliveryDetail, Priority.MEDIUM);
        else
            return createNewTicket(deliveryDetail, Priority.LOW);
    }

    private Ticket createNewTicket(DeliveryDetail deliveryDetail, Priority priority) {
        return Ticket.builder()
                .priority(priority.getDescription())
                .detail(deliveryDetail)
                .createdAt(LocalDateTime.now())
                .build();
    }
}