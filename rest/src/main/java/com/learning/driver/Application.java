package com.learning.driver;

import static com.learning.topic.util.TopicUtil.KAFKA_DATA_PATH;
import static com.learning.topic.util.TopicUtil.KAFKA_TOPICS_ZERO_OFFSET_SEGMENT;
import static com.learning.topic.util.TopicUtil.PARTITION_METADATA_FILE_NAME;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import com.learning.topic.factory.HgTopicFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"com.learning.topic.manager",
    "com.learning.controller",
    "com.learning.validation",
    "com.learning.config"})
public class Application implements CommandLineRunner {

  @Autowired
  private Environment env;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Map<String, Object> configProperties = new HashMap<>();
    configProperties.put("basePath", env.getProperty(KAFKA_DATA_PATH));
    configProperties.put("zeroOffsetSegment", env.getProperty(KAFKA_TOPICS_ZERO_OFFSET_SEGMENT));
    configProperties.put("topicMetadataPerPartionFile", env.getProperty(PARTITION_METADATA_FILE_NAME));
    HgTopicFactory.initializeFactory(configProperties);
  }
}
