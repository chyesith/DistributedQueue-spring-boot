package com.adanfs.distributedqueue.task;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService{
    private Map<String, Task> taskMap = new HashMap<>();


    public void enqueueTask(String taskId){
        Task task = new Task();
        task.setId(taskId);
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        taskMap.put(taskId, task);
    }

    @Override
    public Task getTaskStatusById(String id) {
        return taskMap.getOrDefault(taskMap , null);
    }


    public void updateTaskStatus(String taskId, TaskStatus status, String errorMessage) {
        Task task = taskMap.get(taskId);
        if (task != null) {
            task.setStatus(status);
            task.setUpdatedAt(LocalDateTime.now());
            task.setErrorMessage(errorMessage);
            taskMap.put(taskId, task);
        }
    }

    @Override
    public Map<TaskStatus, List<Task>> getTaskByStatus(TaskStatus status) {
        return null;
    }
}
