package com.adanfs.distributedqueue.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TaskService {


    Task getTaskStatusById(String id);

    Map<TaskStatus , List<Task>> getTaskByStatus(TaskStatus status);
}
