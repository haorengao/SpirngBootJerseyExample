package com.avol.springboot.jersey;

import com.avol.springboot.jersey.config.JerseyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring bootstrapping class.
 *
 * Created by Durga on 7/10/2015.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.avol.springboot.jersey.service",
        "com.avol.springboot.jersey.repository"})
@EntityScan(basePackages = "com.avol.springboot.jersey.domain")
//@EnableAutoConfiguration
public class JerseyApplication {

    @Bean
    public JerseyConfig jerseyConfig(){
        return new JerseyConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(JerseyApplication.class, args);
    }
}
