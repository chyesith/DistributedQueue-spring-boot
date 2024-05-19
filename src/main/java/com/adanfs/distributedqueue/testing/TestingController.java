package com.adanfs.distributedqueue.testing;


import com.adanfs.distributedqueue.producer.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestingController {

    private final RabbitMQProducer rabbitMQProducer;

    public TestingController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam("message")  String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Sent to rabbit mq");
    }
}
