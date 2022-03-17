package com.learning.topic.manager;

import java.io.IOException;
import java.util.Optional;

import com.learning.topic.entity.TopicRequest;
import com.learning.topic.factory.HgTopicFactory;

public class TopicManagerImpl implements TopicManager {

  public TopicManagerImpl() {
  }

  public Topic createTopic(TopicRequest topicRequest) throws IOException {
    return HgTopicFactory.getFactory().getTopicStore().createAndSaveTopic(topicRequest);
  }

  public Optional<Topic> getTopicByName(final String topicName){
    return HgTopicFactory.getFactory().getTopicStore().getTopicByName(topicName);
  }

}
