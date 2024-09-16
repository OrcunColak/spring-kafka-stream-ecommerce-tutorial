package com.colak.springtutorial.stream;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
@Slf4j
public class OrderStreamProcessor {

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> orderStream = streamsBuilder.stream("orders");
        // Process Orders Stream
        orderStream
                .peek((key, value) -> log.info("Received Order: key {} value : {}", key, value))
                .mapValues(this::processOrder)
                .to("processed-orders");

        // Update Inventory
        orderStream
                .peek(this::updateInventory)
                .to("inventory-updated");

        // Send Notifications
        orderStream
                .peek(this::sendNotification)
                .to("notifications");
        return orderStream;
    }

    private String processOrder(String order) {
        // Simulate processing order
        return order + " [Processed]";
    }

    private void updateInventory(String key, String value) {
        // Simulate inventory update
        log.info("Inventory updated for key {} value : {}", key, value);
    }

    private void sendNotification(String key, String order) {
        // Simulate sending notification
        log.info("Notification sent for key {} value : {}", key, order);
    }
}
