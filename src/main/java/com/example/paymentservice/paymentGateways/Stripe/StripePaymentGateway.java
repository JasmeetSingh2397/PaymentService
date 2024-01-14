package com.example.paymentservice.paymentGateways.Stripe;

import com.example.paymentservice.paymentGateways.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.secret_key}")
    private String stripeSecretKey;
    @Override
    public String generatePaymentLink(String email, Long amount, String orderId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        ProductCreateParams productParams =
                ProductCreateParams.builder().setName("Iphone 15").build();
        Product product = Product.create(productParams);

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(amount)
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(params);



        PaymentLinkCreateParams paymentLinkParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        ).setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                        .setRedirect(
                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://amazon.com/?order_id="+orderId).build()
                        ).build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkParams);

        return paymentLink.getUrl();
    }
}
