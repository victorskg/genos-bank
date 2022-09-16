package com.victorskg.genosbankaccountcommand.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Object mapper configuration
 *
 * @author João Victor Claudino Felipe
 * @version 1.0 14/09/2022
 */
@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper createMapper() {
        final var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

}
