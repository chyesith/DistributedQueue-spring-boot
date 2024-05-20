package com.adanfs.distributedqueue.task;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class Task {
    private Long taskId;
    private int priority;

    private String data;

    private TaskStatus status;
}
