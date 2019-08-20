package com.patrykkuchar.app;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer implements Runnable {
  private final Properties properties;

  Consumer(Properties properties) {
    this.properties = properties;
  }

  @Override
  public void run() {
    KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
    List<String>topics = new ArrayList<>();
    topics.add("topic");
    kafkaConsumer.subscribe(topics);
    try {
      while (true) {
        ConsumerRecords<Long, String> records = kafkaConsumer.poll(10);
        for (ConsumerRecord record : records) {
          System.out.println(
              String.format("Thread: " + Thread.currentThread().getName() + " fetching from "+ properties.getProperty("bootstrap.servers") +
                  " Received message: Topic - %s, Partition - %d, Value: %s",
                  record.topic(), record.partition(), record.value()));
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      kafkaConsumer.close();
    }
  }
}
