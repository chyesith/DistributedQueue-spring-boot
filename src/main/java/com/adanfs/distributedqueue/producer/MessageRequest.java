package com.adanfs.distributedqueue.producer;

import lombok.Data;

@Data
public class MessageRequest {
    private int priority;

    private String message;


}
