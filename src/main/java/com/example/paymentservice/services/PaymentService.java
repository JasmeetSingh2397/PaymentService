package com.example.paymentservice.services;

import com.example.paymentservice.models.Orders;
import com.example.paymentservice.models.User;
import com.example.paymentservice.paymentGateways.PaymentGateway;
import com.example.paymentservice.paymentGateways.PaymentGatewayChooserStrategy;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;
    private RestTemplate restTemplate;

    public PaymentService(PaymentGatewayChooserStrategy paymentGatewayChooserStrategy, RestTemplate restTemplate) {

        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy;
        this.restTemplate= restTemplate;
    }

    public String createPaymentLink(Long orderId) throws StripeException {
        Map<String, Long> uriVariables = new HashMap<>();
        uriVariables.put("id", orderId);

        ResponseEntity<Orders> ordersResponseEntity = restTemplate.getForEntity("http://localhost:5000/orders/amount/{id}", Orders.class, uriVariables);
        Orders order= ordersResponseEntity.getBody();
//        Orders order = restTemplate.getForObject("http://localhost:5000/orders/amount/{id}", Orders.class, uriVariables);
//        Orders order= ordersResponseEntity.getBody();

        Long userId= order.getUserId();
        Map<String, Long> userUriVariables = new HashMap<>();
        userUriVariables.put("id", userId);
        ResponseEntity<User> userResponseEntity = restTemplate.getForEntity("http://localhost:9000/users/{id}", User.class, userUriVariables);
        User user= userResponseEntity.getBody();
        String emailOfUser= user.getEmail();
        Double amount= order.getAmount();
        Long longAmount = amount.longValue();

//        Order order= restClient.get(localhost:9090/orders/{orderId})
//        Long userId= order.getUserId();
//        User user= restClient.get(localhost:9000/users/{userId})
//        String emailOfCustomer= user.getEmail();
//        String phoneNumber= user.getPhoneNumber();
//        Long amount= order.getTotalAmount();
        PaymentGateway paymentGateway= paymentGatewayChooserStrategy.choosePaymentGateway();

        return paymentGateway.generatePaymentLink(emailOfUser, longAmount*100, orderId.toString());
//        return paymentGateway.generatePaymentLink("abc@gmail.com", 10000L, orderId.toString());

    }
}
