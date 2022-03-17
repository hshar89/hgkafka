package com.learning.topic.util;

import java.util.Collection;

public class TopicUtil {

  public static final String SEPERATOR = "/";
  public static final String TOPIC_JSON_FILE_NAME = "topics.json";
  public static final String KAFKA_DATA_PATH = "hgkafaka.data.topics.path";
  public static final String KAFKA_TOPICS_ZERO_OFFSET_SEGMENT="hgkafka.data.topics.zeroOffsetSegment";
  public static final String PARTITION_METADATA_FILE_NAME = "hgkafka.data.topics.partition.metadataFileName";

  public static boolean isNullOrEmpty(Collection<?> collection){
    return collection==null || collection.isEmpty();
  }
}
