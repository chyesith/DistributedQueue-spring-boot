package com.adanfs.distributedqueue.producer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/producer")
public class ProducerController {


   private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest message){
        try {
            String messageId = producerService.sendMessage(message);
            return ResponseEntity.ok("Message sent successfully message id.->%S" +messageId);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message.");

        }
    }
}
