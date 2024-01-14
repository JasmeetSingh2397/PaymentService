package com.example.paymentservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orders {
    private Long orderId;
    private Double amount;
    private Long userId;
    private OrderStatus orderStatus;

}
