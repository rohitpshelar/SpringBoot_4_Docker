package com.rt.order;

// Kafka core imports for producer configuration
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * ===========================================
 * 🧩 Kafka Producer Configuration (Order Service)
 * ===========================================
 *
 * This class teaches Spring *how to produce messages to Kafka*.
 * It defines:
 *   1. How to connect to Kafka
 *   2. How to serialize (encode) messages
 *   3. How to get a reusable KafkaTemplate for publishing
 *
 * Analogy:
 *   You are configuring a “mailroom” that can prepare and send parcels.
 */
@Configuration
public class KafkaProducerConfig {

    // Kafka broker URL(s) — where messages are sent to
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    // Schema Registry URL — where message format definitions live
    @Value("${spring.kafka.properties.schema.registry.url}")
    private String registryUrl;


    /**
     * ✉️ Step 1: Create the Producer Factory
     * ---------------------------------------
     *
     * Think of this as your *mailroom factory* — it knows:
     *   - where to send mail (Kafka brokers)
     *   - how to package it (serializers)
     *   - what format rules apply (Avro schemas)
     *
     * It builds and configures Kafka producers.
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();

        // 1️⃣ Kafka broker address (post office)
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);

        // 2️⃣ How to serialize the key
        // Keys are often used for partitioning (e.g., by orderId)
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 3️⃣ How to serialize the value (the actual message)
        // Using Confluent’s Avro serializer ensures the message schema is registered and validated
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                io.confluent.kafka.serializers.KafkaAvroSerializer.class);

        // 4️⃣ Tell serializer where to find the Schema Registry
        props.put("schema.registry.url", registryUrl);

        // (Optional) you can add tuning parameters like:
        // props.put(ProducerConfig.ACKS_CONFIG, "all");       // Wait for all replicas to ack
        // props.put(ProducerConfig.RETRIES_CONFIG, 3);        // Retry failed sends
        // props.put(ProducerConfig.LINGER_MS_CONFIG, 10);     // Batch small messages
        // props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); // Max bytes per batch

        return new DefaultKafkaProducerFactory<>(props);
    }


    /**
     * ✉️ Step 2: Create a KafkaTemplate
     * ---------------------------------
     *
     * Think of this as your *mail-sending tool*.
     *
     * Instead of you manually handling connections and batches,
     * you simply call `kafkaTemplate.send(topic, key, value)`
     * and Spring + Kafka handle the rest (serialization, batching, retries, etc.).
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        // Uses the above factory to create and reuse Kafka producers
        return new KafkaTemplate<>(producerFactory());
    }
}
