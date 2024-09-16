package com.colak.springtutorial.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    private static final int PARTITION_COUNT = 1;
    private static final short REPLICATION_FACTOR = 1;

    @Autowired
    public void configureTopic(KafkaAdmin kafkaAdmin) {
        NewTopic orders = new NewTopic("orders", PARTITION_COUNT, REPLICATION_FACTOR);
        NewTopic processedOrders = new NewTopic("processed-orders", PARTITION_COUNT, REPLICATION_FACTOR);
        NewTopic inventoryUpdated = new NewTopic("inventory-updated", PARTITION_COUNT, REPLICATION_FACTOR);
        NewTopic notifications = new NewTopic("notifications", PARTITION_COUNT, REPLICATION_FACTOR);

        kafkaAdmin.createOrModifyTopics(orders, processedOrders, inventoryUpdated, notifications);
    }
}
