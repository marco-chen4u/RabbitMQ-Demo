package com.marco.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    // fields
    private final String CURRENT_QUEUE = "ExampleQueue";


    @Bean
    Queue getQueue() {
        return new Queue(CURRENT_QUEUE, false);
    }

    @Bean
    Queue getQueueFromBuilder() {
        String queueName = "Example2ndQueue";
        return QueueBuilder.durable(queueName)
                .autoDelete()
                .exclusive()
                .build();
    }
}
