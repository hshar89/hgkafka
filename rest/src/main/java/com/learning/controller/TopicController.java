package com.learning.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.topic.entity.TopicRequest;
import com.learning.topic.exception.TopicValidationException;
import com.learning.topic.manager.Topic;
import com.learning.topic.manager.TopicManager;
import com.learning.validation.Validator;

@RestController
@RequestMapping("/api/v1/hgkafka")
public class TopicController {

  @Autowired
  @Qualifier("topicValidator")
  private Validator topicValidator;

  @Autowired
  private TopicManager topicManager;

  @PostMapping("/createTopic/{topicName}")
  public ResponseEntity<Topic> createTopic(@PathVariable("topicName")String topicName,
                                           @RequestParam(value = "partitions", defaultValue = "1")Integer partitions){
    TopicRequest topicRequest = new TopicRequest(topicName, partitions);
    Topic createdTopic = null;
    try {
      topicValidator.validate(topicRequest);
      topicManager.createTopic(topicRequest);
    } catch(TopicValidationException topicValidationException){
      return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    } catch (IOException e) {
      return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return ResponseEntity.ok(createdTopic);
  }
}
