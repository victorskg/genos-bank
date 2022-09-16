package com.victorskg.genosbankaccountcommand.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka configuration
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 16/09/2022
 */
@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        return props;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory(final ObjectMapper objectMapper) {
        final var keySerializer = new StringSerializer();
        final var valueSerializer = new JacksonJsonSerializer<>(objectMapper);
        return new DefaultKafkaProducerFactory<>(producerConfigs(), keySerializer, valueSerializer);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(final ObjectMapper objectMapper) {
        return new KafkaTemplate<>(producerFactory(objectMapper));
    }

    static class JacksonJsonSerializer<T> extends JsonSerializer<T> {
        JacksonJsonSerializer(final ObjectMapper objectMapper) {
            super(objectMapper);
        }
    }

}
