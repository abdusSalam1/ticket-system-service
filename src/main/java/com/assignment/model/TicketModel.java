package com.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketModel {

    private int deliveryId;
    private String customerType;
    private String deliveryStatus;
    private LocalDateTime expectedDeliveryTime;
    private LocalDateTime timeToReachDestination;
}