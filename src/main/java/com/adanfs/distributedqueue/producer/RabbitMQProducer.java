package com.adanfs.distributedqueue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQProducer {

     private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
     private String exchange;

    private String routingKey;

    private  RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message){
            LOGGER.info(String.format("Message send ->%S" , message));
            rabbitTemplate.convertAndSend("first-topic-exchange","first-test-routing-key" , message);
    }
}
