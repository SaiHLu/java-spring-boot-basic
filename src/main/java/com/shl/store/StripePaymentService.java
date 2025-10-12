package com.shl.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service("stripe")
// @Primary
public class StripePaymentService implements PaymentService {
    @Value("${stripe.api}")
    private String apiUrl;

    @Value("${stripe.enabled}")
    private boolean enabled;

    @Value("${stripe.timeout:3000}")
    private int timeout;

    @Value("${stripe.supported-currencies}")
    private List<String> supportedCurrencies;

    @Override
    public void processPayment(double amount) {
        System.out.println("ApiUrl: " + this.apiUrl +
                "\n Enabled: " + this.enabled + "\n Timeout: "
                + this.timeout + "\n Supported Currencies: "
                + this.supportedCurrencies);
        System.out.println("(Stripe) Amount: " + amount);
    }
}
