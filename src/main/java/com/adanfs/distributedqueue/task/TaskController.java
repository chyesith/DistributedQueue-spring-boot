package com.adanfs.distributedqueue.task;

import com.adanfs.distributedqueue.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/task")
public class TaskController {


    private final RabbitMQProducer rabbitMQProducer;

    private TaskService taskService;


    public TaskController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }



    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest message){
       try {
           String messageId = rabbitMQProducer.sendMessage(message);
           return ResponseEntity.ok("Message sent successfully message id.->%S" +messageId);
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message.");

       }
    }

    @GetMapping("/status/{taskId}")
    public ResponseEntity<Task> statusCheck(@PathVariable String id){
        try {
           Task task = taskService.getTaskStatusById(id);
            return ResponseEntity.ok(task);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Task());

        }
    }

    @PostMapping("/enqueue/priority")
    public void enqueueWithPriority() {

    }
}
