package com.adanfs.distributedqueue.task;

import com.adanfs.distributedqueue.producer.RabbitMQProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/task")
public class TaskController {


    private final RabbitMQProducer rabbitMQProducer;

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

    @PostMapping("/enqueue/priority")
    public void enqueueWithPriority() {

    }
}
