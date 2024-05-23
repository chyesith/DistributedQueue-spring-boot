package com.adanfs.distributedqueue.status;

import java.util.List;
import java.util.Map;

public interface StatusService {
    Task getTaskStatusById(String id);

    Map<TaskStatus, List<Task>> getTaskByStatus(TaskStatus status);
}
