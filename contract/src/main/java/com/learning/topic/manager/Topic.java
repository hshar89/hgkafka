package com.learning.topic.manager;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Topic {
  @JsonProperty("topicName")
  private String topicName;
  @JsonProperty("topicId")
  private String topicId;
  @JsonProperty("partitions")
  private Integer partitions;

  private Topic() {
  }

  public Topic(String topicName, String topicId, int partitions) {
    this.topicName = topicName;
    this.topicId = topicId;
    this.partitions = partitions;
  }

  public String getTopicName() {
    return topicName;
  }

  public String getTopicId() {
    return topicId;
  }

  public int getPartitions() {
    return partitions;
  }

}
