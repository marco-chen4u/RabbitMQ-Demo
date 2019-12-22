package com.marco.rabbitmq.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {
    private final String EXCHANGE_NAME = "ExampleExchange";

    @Bean
    Exchange getExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Exchange getDirectExchangeFromBuilder() {
        String exchangeName = "Example2ndExchange";
        return ExchangeBuilder.directExchange(exchangeName)
                .autoDelete()
                .build();
    }

    @Bean
    Exchange getTopicExchangeFromBuilder() {
        String exchangeName = "TopicTestExchange";
        return ExchangeBuilder.topicExchange(exchangeName)
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    Exchange getFanoutExchangeFromBuilder() {
        String exchangeName = "FanoutTestExchange";
        return ExchangeBuilder.fanoutExchange(exchangeName)
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }

    @Bean
    Exchange getHeaderExchangeFromBuilder() {
        String exchangeName = "HeaderTestExchange";
        return ExchangeBuilder.headersExchange(exchangeName)
                .internal()
                .durable(true)
                .ignoreDeclarationExceptions()
                .build();
    }
}
