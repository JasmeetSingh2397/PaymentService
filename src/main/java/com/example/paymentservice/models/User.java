package com.example.paymentservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User {
    private Set<Role> roles=new HashSet<>();
    private String email;
}
