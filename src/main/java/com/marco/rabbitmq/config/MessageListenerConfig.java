package com.marco.rabbitmq.config;

import com.marco.rabbitmq.listener.RabbitMQMessageListener;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.amqp.core.Queue;
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
    private final String CURRENT_QUEUE = "RoutedQueue";
    private final String HOST_NAME = "localhost";
    private final String USER_NAME = "guest";
    private final String PASSWORD = "guest";

    @Bean
    public Queue getQueue() {
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
        simpleMessageListenerContainer.setQueues(getQueue()); // it could add more than 1 queues
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;
    }

}
