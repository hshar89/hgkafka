package com.learning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hgkafka")
public class ProducerController {

  @PostMapping("/publishMessage")
  public ResponseEntity<Void> publishMessage(@RequestParam("topicName") String topicName,
                                             @RequestParam("partition") int partition) {

    return null;
  }

}
