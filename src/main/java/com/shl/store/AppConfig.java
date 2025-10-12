package com.shl.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Value("${payment}")
    private String payment;

    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal() {
        return new PaypalPaymentService();
    }

    @Bean
    @Lazy
    // @Scope("singleton") // single instance (default)
    // @Scope("prototype") // new instance every time
    // @Scope("request") // new instance per HTTP request
    // @Scope("session") // new instance per HTTP session
    public OrderService orderService() {
        if (this.payment.equals("paypal")) {
            return new OrderService(paypal());
        }

        return new OrderService(stripe());
    }
}
