package com.patrykkuchar.app;

import java.util.Properties;

// TODO: 13.08.2019 obecnie z jakiś przyczyn tworzy tylko jedną partycję
// TODO: 13.08.2019 spróbować skonsumować z innych portów najlepiej równolegle
// TODO: 13.08.2019 konsumer czasem nie czyta, oprócz tego katalogi logów są tworzone jako .log1 -> przez liczbe w nazwie???
  public class Main {
  public static void main(String[] args) throws InterruptedException {
    Properties properties_1 = new Properties();
    properties_1.put("bootstrap.servers", "localhost:9091");
    properties_1.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties_1.put(
        "value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties_1.put("group.id", "test-group0");

    Properties properties_2 = new Properties();
    properties_2.put("bootstrap.servers", "localhost:9093");
    properties_2.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties_2.put(
            "value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties_2.put("group.id", "test-group1");

    Properties properties_3 = new Properties();
    properties_3.put("bootstrap.servers", "localhost:9090");
    properties_3.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties_3.put(
            "value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    properties_3.put("group.id", "test-group2");

    Thread consumer_1 = new Thread(new Consumer(properties_1), "consumer_1");
    Thread consumer_2 = new Thread(new Consumer(properties_2), "consumer_2");
    Thread consumer_3 = new Thread(new Consumer(properties_3), "consumer_3");

   consumer_1.start();
   consumer_2.start();
    consumer_3.start();

  }
}
