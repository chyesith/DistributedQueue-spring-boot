package com.adanfs.distributedqueue.task;

import lombok.Data;

@Data
public class Task {
    private String taskId;
    private int priority;

    private String data;
}
