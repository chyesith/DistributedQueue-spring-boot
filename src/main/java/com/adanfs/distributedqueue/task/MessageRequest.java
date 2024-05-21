package com.adanfs.distributedqueue.task;

import lombok.Data;

@Data
public class MessageRequest {
    private int priority;

    private String message;


}
