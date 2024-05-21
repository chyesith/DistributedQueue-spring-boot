package com.adanfs.distributedqueue.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
//    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
//
//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void processHighPriorityMessage(String message){
//        LOGGER.info(String.format("Received message -> %s" , message));
//    }
//
//
//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void processMediumPriorityMessage(String message){
//        LOGGER.info(String.format("Received message -> %s" , message));
//    }
//
//
//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void processLowPriorityMessage(String message){
//        LOGGER.info(String.format("Received message -> %s" , message));
//    }


}
