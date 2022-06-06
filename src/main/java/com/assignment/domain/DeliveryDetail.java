package com.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Table(name = "delivery_detail")
public class DeliveryDetail {

    @Id
    @Column(name = "delivery_id")
    private final int deliveryId;
    @Column(name = "customer_type")
    private final String customerType;
    @Column(name = "delivery_status")
    private final String deliveryStatus;
    @Column(name = "expected_delivery_time")
    private final LocalDateTime expectedDeliveryTime;
    @Column(name = "current_distance_from_destination_in_meters")
    private final int distanceFromDestination;
    @Column(name = "time_to_reach_destination")
    private final LocalDateTime timeToReachDestination;
    @Column(name = "mean_time_to_prepare_food_in_minutes")
    private final int meanTimeToPrepareFood;
    @Column(name = "rider_rating")
    private final int riderRating;

    public DeliveryDetail() {
        this.deliveryId = 0;
        this.customerType = "";
        this.deliveryStatus = "";
        this.expectedDeliveryTime = LocalDateTime.now();
        this.distanceFromDestination = 0;
        this.timeToReachDestination = LocalDateTime.now();
        this.meanTimeToPrepareFood = 0;
        this.riderRating = 0;
    }

    public boolean isHighPriorityOrder() {
        return isVIPCustomer() || isExpectedTimePassed() || isEstimateGreaterThanExpected();
    }

    public boolean isMediumPriority() {
        return isLoyalCustomer();
    }

    private boolean isEstimateGreaterThanExpected() {
        return timeToReachDestination.plusMinutes(meanTimeToPrepareFood).isAfter(expectedDeliveryTime);
    }

    private boolean isVIPCustomer() {
        return !StringUtils.isBlank(customerType) && StringUtils.equalsIgnoreCase("VIP", customerType);
    }

    private boolean isLoyalCustomer() {
        return StringUtils.equalsIgnoreCase("Loyal", customerType);
    }

    private boolean isExpectedTimePassed() {
        return expectedDeliveryTime != null && LocalDateTime.now().isBefore(expectedDeliveryTime)
                && !StringUtils.equalsIgnoreCase("Order Delivered", deliveryStatus);
    }
}
