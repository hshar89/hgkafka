package com.learning.topic.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.learning.topic.entity.TopicRequest;
import com.learning.topic.manager.Topic;
import com.learning.topic.util.TopicUtil;

public class InMemoryFileTopicStore extends FileTopicStore {

  private final Map<String, Topic> topicMap;

  public InMemoryFileTopicStore(Map configProperties) {
    super(configProperties);
    this.topicMap = new HashMap<>();
  }

  @Override
  public void initialize() throws IOException {
    super.initialize();
    Collection<Topic> currentTopics = super.getCurrentTopicsFromJson();
    if(!TopicUtil.isNullOrEmpty(currentTopics)){
      for(Topic topic: currentTopics){
        topicMap.put(topic.getTopicName(), topic);
      }
    }
  }

  @Override
  public List<Topic> getCurrentTopics() throws IOException {
    return new ArrayList<>(topicMap.values());
  }

  @Override
  public Topic createAndSaveTopic(TopicRequest topicRequest) throws IOException {
    Topic topic = super.createAndSaveTopic(topicRequest);
    topicMap.put(topic.getTopicName(), topic);
    return topic;
  }

  @Override
  public Optional<Topic> getTopicByName(String topicName) {
    return Optional.ofNullable(topicMap.get(topicName));
  }

  @Override
  public Optional<Topic> getTopicById(String topicId) {
    return topicMap.values().stream().filter(topic -> topic.getTopicId().equals(topicId)).findAny();
  }
}
