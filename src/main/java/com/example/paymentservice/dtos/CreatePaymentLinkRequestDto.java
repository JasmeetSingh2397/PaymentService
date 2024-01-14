package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreatePaymentLinkRequestDto {

    private Long orderId;

}
