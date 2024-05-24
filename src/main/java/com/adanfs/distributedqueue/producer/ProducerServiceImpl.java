package com.adanfs.distributedqueue.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ProducerServiceImpl implements ProducerService {

     private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.keyname}")
    private String routingKey;


    private final RabbitTemplate rabbitTemplate;

    public ProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public String sendMessage(MessageRequest message){
        //TODO make proper id
        String messageId = UUID.randomUUID().toString();

        Message rabbitMQMessage = MessageBuilder.withBody(message.getMessage().getBytes())
                .setMessageId(messageId)
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .setPriority(message.getPriority())
                .build();

        LOGGER.info(String.format("Message send ->%S" , rabbitMQMessage/**/));

        rabbitTemplate.convertAndSend(exchangeName,routingKey , rabbitMQMessage);
        return messageId;
    }


}
