package com.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(name = "priority")
    private final int priority;
    @OneToOne(targetEntity = DeliveryDetail.class)
    private final DeliveryDetail detail;
    @Column(name = "createdAt")
    private final LocalDateTime createdAt;

    public Ticket() {
        this.id = null;
        this.priority = 0;
        this.detail = null;
        this.createdAt=null;
    }

    public int getDeliveryId() {
        return detail.getDeliveryId();
    }

    public String getCustomerType() {
        return detail.getCustomerType();
    }

    public String getDeliveryStatus() {
        return detail.getDeliveryStatus();
    }

    public LocalDateTime getExpectedDeliveryTime() {
        return detail.getExpectedDeliveryTime();
    }

    public LocalDateTime getTimeToReachDestination() {
        return detail.getTimeToReachDestination();
    }
}
