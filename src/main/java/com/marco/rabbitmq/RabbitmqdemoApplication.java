package com.marco.rabbitmq;

import com.marco.rabbitmq.pojo.SimpleMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqdemoApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleMessage simpleMessage = new SimpleMessage("FirstMessage", "This is a simple description");
        String exchange = "RoutedExchange";
        String routingKey = "routedTopic";

        rabbitTemplate.convertAndSend(exchange, routingKey, simpleMessage);
    }
}
