package com.adanfs.distributedqueue.scheduler;

public interface SchedulerService {
    void processDelayedMessage(String message);
    void processScheduledMessages ();
}
