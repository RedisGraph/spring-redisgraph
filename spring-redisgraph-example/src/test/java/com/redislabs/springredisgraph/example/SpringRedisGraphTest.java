package com.redislabs.springredisgraph.example;

import com.redislabs.redisgraph.Record;
import com.redislabs.redisgraph.RedisGraph;
import com.redislabs.redisgraph.ResultSet;
import com.redislabs.redisgraph.graph_entities.Node;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringRedisGraphTest {
    @Autowired
    RedisGraph graph;

    @Test
    void testExample() {
        ResultSet rs = graph.query("g", "CREATE (n {text : 'Hello World!'}) RETURN n");
        assertTrue(rs.hasNext(), "ResultSet should have one record");
        Record r = rs.next();
        Node n = r.getValue("n");
        assertNotNull(n, "Record should hold a node");
        assertEquals("Hello World!", n.getProperty("text").getValue(), "Node should have test field with " +
                "'Hello World!' content");
    }
}