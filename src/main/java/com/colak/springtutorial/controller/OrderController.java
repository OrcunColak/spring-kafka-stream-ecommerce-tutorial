package com.colak.springtutorial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")

@RequiredArgsConstructor
public class OrderController {

    private final KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        kafkaTemplate.send("orders", order.toString());
        return ResponseEntity.ok("Order placed successfully");
    }
}