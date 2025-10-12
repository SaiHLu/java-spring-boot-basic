package com.shl.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// @Service
public class OrderService {
    private PaymentService paymentService;

    // @Qualifier("stripe")
    public OrderService(PaymentService paymentService) {
        System.out.println("OrderService initialized");
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("OrderService PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("OrderService PreDestroy");
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        this.paymentService.processPayment(10);
    }
}
