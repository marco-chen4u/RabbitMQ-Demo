package com.marco.rabbitmq.config;

import com.marco.rabbitmq.listener.RabbitMQMessageListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configurations for MessageListener to Queues
 *
 * (1)Define the Queue to Listen to
 * (2)Provide the Connection to the Queue
 * (3)Bind the Queue, Connection and Listener
 */

@Configuration
public class RabbitMQConfig {
    // fields
    private final String CURRENT_EXCHANGE = "RoutedExchange";
    //private final String CURRENT_BINDING = "";
    private final String CURRENT_ROUTING_KEY = "routedTopic";// routing key, message grouping
    private final String CURRENT_QUEUE = "RoutedQueue";
    private final String HOST_NAME = "localhost";
    private final String USER_NAME = "guest";
    private final String PASSWORD = "guest";

    @Bean
    public Exchange getRoutedExchange() {
        return ExchangeBuilder.topicExchange(CURRENT_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Binding getRoutedBinding() {
        return BindingBuilder
                .bind(getRoutedQueue())
                .to(getRoutedExchange())
                .with(CURRENT_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Queue getRoutedQueue() {
        return new Queue(CURRENT_QUEUE, true);
    }

    @Bean
    ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(HOST_NAME);
        cachingConnectionFactory.setUsername(USER_NAME);
        cachingConnectionFactory.setPassword(PASSWORD);
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer getMessageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(getConnectionFactory());
        simpleMessageListenerContainer.setQueues(getRoutedQueue()); // it could add more than 1 queues
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;
    }

}
