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
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

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

    // Login to the database and set the password of redis graph with the below command
    // redis-cli$: CONFIG SET requirepass "Redis@password123"
    private String password;

    @Bean(destroyMethod = "close")
    public RedisGraph redisGraphConnection() {
        if (null == password) {
            return new com.redislabs.redisgraph.impl.api.RedisGraph();
        }
        else {
            JedisPool pool = new JedisPool(new JedisPoolConfig(), host, port, Protocol.DEFAULT_TIMEOUT, password);
            return new com.redislabs.redisgraph.impl.api.RedisGraph(pool);
        }
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

    private String password() {
        if (password == null) {
            return props.getPassword();
        }
        return password;
    }
}
