package com.shl.store;

import org.springframework.stereotype.Service;

// @Service("paypal")
public class PaypalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("(Paypal) Amount: " + amount);
    }
}
