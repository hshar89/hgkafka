package com.learning.topic.entity;

public class TopicRequest {

  private final String topicName;
  private final int partitions;
  public TopicRequest(String topicName, int partitions){
    this.topicName = topicName;
    this.partitions = partitions;
  }

  public String getTopicName() {
    return topicName;
  }

  public int getPartitions() {
    return partitions;
  }
}
