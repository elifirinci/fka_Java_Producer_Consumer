package org.example;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.ByteArraySerializer;

import java.io.*;
import java.util.Properties;

public class ProducerApp {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "pkc-619z3.us-east1.gcp.confluent.cloud:9092"); // ✅ Doğru Kafka endpoint
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required " +
                        "username=\"<API-KEY>\" " +
                        "password=\"<API-SECRET>\";");
        props.put("key.serializer", ByteArraySerializer.class.getName());
        props.put("value.serializer", ByteArraySerializer.class.getName());

        KafkaProducer<byte[], byte[]> producer = new KafkaProducer<>(props);

        Student student = new Student(1, "Elif");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(student);
        out.flush();

        byte[] serialized = bos.toByteArray();

        ProducerRecord<byte[], byte[]> record = new ProducerRecord<>("student-topic", null, serialized);
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.printf("✅ Sent: %s (offset: %d, partition: %d)%n", student, metadata.offset(), metadata.partition());
            } else {
                System.err.println("❌ Error sending message: " + exception.getMessage());
            }
        });

        producer.close();
    }
}
