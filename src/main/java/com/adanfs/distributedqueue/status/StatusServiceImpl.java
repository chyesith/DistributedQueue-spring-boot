package com.adanfs.distributedqueue.status;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatusServiceImpl implements StatusService{
    @Override
    public Task getTaskStatusById(String id) {
        return null;
    }

    @Override
    public Map<TaskStatus, List<Task>> getTaskByStatus(TaskStatus status) {
        return null;
    }
}
