package com.redislabs.springredisgraph.example;

import com.redislabs.springredisgraph.RedisGraphConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {  RedisGraphConfiguration.class })
public class SpringRedisGraph {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisGraph.class, args);

    }
}
