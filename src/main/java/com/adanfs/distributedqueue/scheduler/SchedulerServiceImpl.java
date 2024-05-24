package com.adanfs.distributedqueue.scheduler;


import com.adanfs.distributedqueue.producer.ProducerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);




    @Override
    @RabbitListener(queues = {"${rabbitmq.dlQueue.name}"}) // get messages from dead letter queue
    public void processDelayedMessage(String message) {
        LOGGER.info("Received delayed message: {} " + message);
        // logic for process
    }


        @Override
        @Scheduled(fixedDelay = 10000) // scheduler that can process the messages for every 10 second
        public void processScheduledMessages () {

        }


    }