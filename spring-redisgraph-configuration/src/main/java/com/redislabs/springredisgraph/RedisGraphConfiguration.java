package com.redislabs.springredisgraph;

import com.redislabs.redisgraph.RedisGraph;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redisgraph")
@Data
@ComponentScan
public class RedisGraphConfiguration {

    @Autowired
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private RedisProperties props;

    private String host;
    private Integer port;


    @Bean(destroyMethod = "close")
    public RedisGraph redisGraphConnection() {
        return new com.redislabs.redisgraph.impl.api.RedisGraph(host(), port());
    }

    private String host() {
        if (host == null) {
            return props.getHost();
        }
        return host;
    }

    private int port() {
        if (port == null) {
            return props.getPort();
        }
        return port;
    }

}
