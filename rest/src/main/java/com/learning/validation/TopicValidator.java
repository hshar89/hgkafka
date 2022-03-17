package com.learning.validation;

import org.springframework.stereotype.Component;

import com.learning.topic.entity.TopicRequest;
import com.learning.topic.exception.TopicValidationException;
import com.learning.topic.factory.HgTopicFactory;

@Component("topicValidator")
public class TopicValidator implements Validator<TopicRequest> {

  public TopicValidator(){}
  @Override
  public void validate(TopicRequest topicRequest) throws TopicValidationException {
      if(topicRequest.getPartitions()>100){
        throw new TopicValidationException("More Than 100 partitions are not supported");
      }
    if(HgTopicFactory.getFactory().getTopicManager().getTopicByName(topicRequest.getTopicName()).isPresent()){
      throw new TopicValidationException("Topic Already Exists");
    }
  }
}
