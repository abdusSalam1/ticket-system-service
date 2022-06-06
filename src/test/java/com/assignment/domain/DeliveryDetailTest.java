package com.assignment.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DeliveryDetailTest {

    @Test
    public void whenVipCustomerShouldReturnHighPriorityTrue() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "vip", "Order Preparing", now, 1, now,2,1);
        //when
        boolean result = deliveryDetail.isHighPriorityOrder();
        //than
        assertTrue(result);
    }

    @Test
    public void whenCustomerTypeIsNullShouldReturnAsLowPriority() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, null, "Order Preparing", now, 1, now,2,1);
        //when
        boolean highPriorityOrder = deliveryDetail.isHighPriorityOrder();
        boolean mediumPriorityOrder = deliveryDetail.isMediumPriority();

        //than
        assertFalse(highPriorityOrder);
        assertFalse(mediumPriorityOrder);
    }

    @Test
    public void whenCustomerTypeIsEmptyShouldReturnAsLowPriority() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "", "Order Preparing", now, 1, now,2,1);
        //when
        boolean highPriorityOrder = deliveryDetail.isHighPriorityOrder();
        boolean mediumPriorityOrder = deliveryDetail.isMediumPriority();

        //than
        assertFalse(highPriorityOrder);
        assertFalse(mediumPriorityOrder);
    }

    @Test
    public void whenCustomerTypeIsBlankShouldReturnAsLowPriority() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, " ", "Order Preparing", now, 1, now,2,1);
        //when
        boolean highPriorityOrder = deliveryDetail.isHighPriorityOrder();
        boolean mediumPriorityOrder = deliveryDetail.isMediumPriority();

        //than
        assertFalse(highPriorityOrder);
        assertFalse(mediumPriorityOrder);
    }

    @Test
    public void whenExpectedTimeIsBreachedAndOrderIsPendingShouldReturnHighPriorityTrue() {
        //given
        LocalDateTime expectedDelivery = LocalDateTime.of(2030, 12, 1, 00, 00);
        LocalDateTime timeToReach = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "loyal", "Order Preparing", expectedDelivery, 1, timeToReach,2,1);
        //when
        boolean result = deliveryDetail.isHighPriorityOrder();
        //than
        assertTrue(result);
    }

    @Test
    public void whenLoyalCustomerShouldReturnMediumPriorityTrue() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "loyal", "Order Preparing", now, 1, now,2,1);
        //when
        boolean result = deliveryDetail.isMediumPriority();
        //than
        assertTrue(result);
    }

    @Test
    public void whenMeantimeIsGreaterThanExpectedShouldReturnHighPriorityTrue() {
        //given
        LocalDateTime now = LocalDateTime.now();
        DeliveryDetail deliveryDetail = new DeliveryDetail(1, "loyal", "Order Preparing", now, 1, now,30,1);
        //when
        boolean result = deliveryDetail.isHighPriorityOrder();
        //than
        assertTrue(result);
    }

}