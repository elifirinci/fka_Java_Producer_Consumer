package org.example;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;

import java.io.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerApp {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "pkc-619z3.us-east1.gcp.confluent.cloud:9092"); 
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                        "username=\"\" " +
                        "password=\"\";");
        props.put("group.id", "student-group");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", ByteArrayDeserializer.class.getName());
        props.put("value.deserializer", ByteArrayDeserializer.class.getName());

        KafkaConsumer<byte[], byte[]> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("student-topic"));

        while (true) {
            ConsumerRecords<byte[], byte[]> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<byte[], byte[]> record : records) {
                try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(record.value());
                    ObjectInputStream in = new ObjectInputStream(bis);
                    Student student = (Student) in.readObject();
                    System.out.println("Received: " + student);
                } catch (Exception e) {
                    System.err.println("Deserialize error: " + e.getMessage());
                }
            }
        }
    }
}
