package com.adanfs.distributedqueue.producer;

public interface ProducerService {
    String sendMessage(MessageRequest message);
}
