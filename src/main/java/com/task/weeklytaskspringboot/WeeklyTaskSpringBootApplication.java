package com.task.weeklytaskspringboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeeklyTaskSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeeklyTaskSpringBootApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

}
