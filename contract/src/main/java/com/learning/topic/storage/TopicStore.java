package com.learning.topic.storage;

import com.learning.topic.entity.TopicRequest;
import com.learning.topic.manager.Topic;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TopicStore {

  void initialize() throws IOException;
  List<Topic> getCurrentTopics() throws IOException;
  Topic createAndSaveTopic(TopicRequest topicRequest) throws IOException;
  Optional<Topic> getTopicByName(String topicName);
  Optional<Topic> getTopicById(String topicId);
}
