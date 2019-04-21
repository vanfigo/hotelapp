package com.bucketdev.hotelapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class JacksonConfirutation {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        setDateFormat(objectMapper);

        return objectMapper;
    }

    private static void setDateFormat(ObjectMapper mapper) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false); // not necessary but I would recommend
        mapper.setDateFormat(df);
    }

}
