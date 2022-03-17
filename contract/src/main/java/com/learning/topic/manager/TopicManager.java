package com.learning.topic.manager;

import com.learning.topic.entity.TopicRequest;

import java.io.IOException;
import java.util.Optional;

public interface TopicManager {
  Topic createTopic(TopicRequest topicRequest) throws IOException;
  Optional<Topic> getTopicByName(String topicName);
}
