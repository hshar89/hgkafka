package com.learning.topic.factory;

import java.io.IOException;
import java.util.Map;

import com.learning.topic.manager.TopicManager;
import com.learning.topic.manager.TopicManagerImpl;
import com.learning.topic.storage.InMemoryFileTopicStore;
import com.learning.topic.storage.TopicStore;

public class HgTopicFactory {

  private final TopicManager topicManager;

  private final TopicStore topicStore;

  private static HgTopicFactory INSTANCE =null;

  public static void initializeFactory(Map configProperties) throws IOException {
    if(INSTANCE==null){
      INSTANCE = new HgTopicFactory(configProperties);
    }
  }

  public static HgTopicFactory getFactory(){
    return INSTANCE;
  }

  private HgTopicFactory(Map<String, Object> configProperties) throws IOException {
    this.topicStore = new InMemoryFileTopicStore(configProperties);
    this.topicManager = new TopicManagerImpl();
    topicStore.initialize();
  }

  public TopicManager getTopicManager() {
    return topicManager;
  }

  public TopicStore getTopicStore() {
    return topicStore;
  }
}
