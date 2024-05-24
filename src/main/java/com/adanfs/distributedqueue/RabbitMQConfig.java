package com.adanfs.distributedqueue;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
   // private final ConnectionFactory connectionFactory;


    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.dlQueue.name}")
    private String deadLetterQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.dlExchange.name}")
    private String deadLetterExchange;

    @Value("${rabbitmq.routing.keyname}")
    private String routingKey;

    @Value("${rabbitmq.queue.max.priority}")
    private int maxPriority;

  /*  @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return  factory;
    }*/

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(deadLetterQueueName).build();
    }

    // Bind DLX queue to DLX exchange
    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("#");
    }

    // Define DLX exchange
    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange(deadLetterExchange);
    }


    @Bean
    public Queue queue() {
        //make priority queue using variable that configurable
        return QueueBuilder.durable(queueName)
                .maxPriority(maxPriority).build();
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }


    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }
}
