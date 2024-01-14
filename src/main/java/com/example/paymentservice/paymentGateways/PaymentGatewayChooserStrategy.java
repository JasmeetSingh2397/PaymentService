package com.example.paymentservice.paymentGateways;

import com.example.paymentservice.paymentGateways.Razorpay.RazorpayPaymentGateway;
import com.example.paymentservice.paymentGateways.Stripe.StripePaymentGateway;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {
    private StripePaymentGateway stripePaymentGateway;
    private RazorpayPaymentGateway razorpayPaymentGateway;

    public PaymentGatewayChooserStrategy(StripePaymentGateway stripePaymentGateway, RazorpayPaymentGateway razorpayPaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
        this.razorpayPaymentGateway = razorpayPaymentGateway;
    }

    public PaymentGateway choosePaymentGateway(){
        //Business Logic here
        return stripePaymentGateway;
    }

}
