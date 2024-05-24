package com.adanfs.distributedqueue.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements WorkerService {

    private static final int MAX_RETRIES = 5;
    private static final long INITIAL_BACKOFF = 1000;


    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void receiveMessage(String message) throws InterruptedException {
        LOGGER.info("Received message: {}", message);
        boolean success = processMessageWithRetry(message, 0);
        if (!success) {
            // Handle failure after retries
            LOGGER.info("Failed to process message after retries: " + message);
        }
    }

    //this implementation using threads
    private boolean processMessageWithRetry(String message, int retryCount) throws InterruptedException {
        try {
            // Process the task method here

            return true;
        } catch (TransientFailureException e) {
            if (retryCount < MAX_RETRIES) {
                long backoff = INITIAL_BACKOFF * (1 << retryCount);
                Thread.sleep(backoff);
                return processMessageWithRetry(message, retryCount + 1);
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.info("transient failur: {}"+ e);
            return false;
        }
    }



   // this implementation with spring retry
    @Retryable(value = {TransientFailureException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000, multiplier = 2))
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void processMessageWithRetry(String message) {
        try {

            LOGGER.info("Received message: {}", message);
            if (Math.random() < 0.5) {
                throw new TransientFailureException("Simulated transient failure");
            }
        } catch (TransientFailureException e) {
            LOGGER.error("Transient failure occurred: {}", e.getMessage());
            throw e;
        }
    }

    // Custom exception class for transient failures
    private static class TransientFailureException extends RuntimeException {
        public TransientFailureException(String message) {
            super(message);
        }
    }

}
