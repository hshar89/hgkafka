package com.learning.topic.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.topic.entity.TopicRequest;
import com.learning.topic.manager.Topic;

import static com.learning.topic.util.TopicUtil.SEPERATOR;
import static com.learning.topic.util.TopicUtil.TOPIC_JSON_FILE_NAME;

public abstract class FileTopicStore implements TopicStore{

  private final ObjectMapper objectMapper;
  private final String basePath;
  private final String zeroOffsetSegment;
  private final String metadataFileName;

  protected FileTopicStore(Map configProperties) {
    this.zeroOffsetSegment = (String) configProperties.get("zeroOffsetSegment");;
    this.metadataFileName = (String) configProperties.get("topicMetadataPerPartionFile");
    this.objectMapper = new ObjectMapper();
    this.basePath =  (String)configProperties.get("basePath");;
  }

  @Override
  public void initialize() throws IOException {
    File baseDirectory = new File(basePath);
    if (baseDirectory.exists() == false) {
      baseDirectory.mkdirs();
    }
    final String topicsFilePath = getTopicFilePath(basePath);
    final ObjectMapper objectMapper = new ObjectMapper();
    File topicsFile = new File(topicsFilePath);
    if (!topicsFile.exists()) {
      topicsFile.createNewFile();
      objectMapper.writeValue(Paths.get(topicsFilePath).toFile(), new Topic[0]);
    }
  }

  protected Collection<Topic> getCurrentTopicsFromJson() throws IOException {
    final String topicsFilePath = getTopicFilePath(basePath);
    List<Topic> topics = Arrays.asList(objectMapper.readValue(Paths.get(topicsFilePath).toFile(), Topic[].class));
    return new ArrayList<>(topics);
  }

  @Override
  public Topic createAndSaveTopic(TopicRequest topicRequest) throws IOException {
    Topic topic = saveTopicToFile(topicRequest);
    final String topicId  = topic.getTopicId();
    for(int i=0;i<topicRequest.getPartitions();i++){
      String filePath = getPartitionFolderPath(basePath,topicRequest.getTopicName(), i);
      File baseDirectory = new File(filePath);
      if(baseDirectory.exists()==false){
        baseDirectory.mkdirs();
      }
      String segmentWithOffsetZeroPath = getPartitionFilePath(filePath,zeroOffsetSegment);
      String metadataFilePath = getPartitionMetadataFilePath(filePath, metadataFileName);
      File partitionFile = new File(segmentWithOffsetZeroPath);
      partitionFile.createNewFile();
      File metadataFile = new File(metadataFilePath);
      FileWriter fileWriter = new FileWriter(metadataFile);
      fileWriter.write("topicId: "+topicId);
      fileWriter.close();
    }
    return topic;
  }

  private Topic saveTopicToFile(TopicRequest topicRequest) throws IOException {
    final String topicId = UUID.randomUUID().toString();
    Topic topic = new Topic(topicRequest.getTopicName(), topicId, topicRequest.getPartitions());
    List<Topic> currentTopics = this.getCurrentTopics();
    currentTopics.add(topic);
    final String topicsFilePath = getTopicFilePath(basePath);
    objectMapper.writeValue(Paths.get(topicsFilePath).toFile(), currentTopics.toArray());
    return topic;
  }

  private static String getPartitionFolderPath(final String basePath, final String topicName, int offset){
    return basePath+SEPERATOR+topicName+"_"+offset;
  }

  private static String getPartitionFilePath(final String folderPath, final String zeroOffsetSegment){
    return folderPath+SEPERATOR+zeroOffsetSegment+".log";
  }

  private static String getPartitionMetadataFilePath(final String folderPath, final String metadataFileName){
    return folderPath+SEPERATOR+metadataFileName;
  }

  private static String getTopicFilePath(final String basePath) {
    return basePath + SEPERATOR + TOPIC_JSON_FILE_NAME;
  }

}
