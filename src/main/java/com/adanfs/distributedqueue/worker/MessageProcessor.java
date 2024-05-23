package com.adanfs.distributedqueue.worker;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageProcessor {

    private static final int MAX_RETRIES = 10;
    private static final int BASE_DELAY_MS = 1000; //

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processMessage(Message message) {
        int retryCount = 0;
        boolean success = false;
        while (!success && retryCount < MAX_RETRIES) {
            try {
                // TODO ask which kind of loigc expect
                process(message);
                success = true;
            } catch (Exception e) {
                retryCount++;
                long delay = calculateDelay(retryCount);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (!success) {
            // TODO log or make a failed task after max attemts
        }
    }

    private void process(Message message) {
        //TODO process logic what kind of message process need
    }

    private long calculateDelay(int retryCount) {
        return (long) (BASE_DELAY_MS * Math.pow(2, retryCount));
    }
}
