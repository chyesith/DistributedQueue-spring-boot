package com.adanfs.distributedqueue.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/status")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }


    @GetMapping("/status/{taskId}")
    public ResponseEntity<Task> statusCheck(@PathVariable String id){
        try {
            Task task = statusService.getTaskStatusById(id);
            return ResponseEntity.ok(task);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Task());

        }
    }
}
