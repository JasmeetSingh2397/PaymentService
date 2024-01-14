package com.example.paymentservice.paymentGateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {

    String generatePaymentLink(String email, Long amount, String orderId) throws StripeException;
}
