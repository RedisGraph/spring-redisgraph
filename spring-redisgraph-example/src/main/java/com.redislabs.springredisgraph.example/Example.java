package com.redislabs.springredisgraph.example;

import com.redislabs.redisgraph.Record;
import com.redislabs.redisgraph.RedisGraph;
import com.redislabs.redisgraph.ResultSet;
import com.redislabs.redisgraph.graph_entities.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Example implements CommandLineRunner {
    @Autowired
    RedisGraph graph;

    @Override
    public void run(String... args) {
        ResultSet rs = graph.query("g", "CREATE (n {text : 'Hello World!'}) RETURN n");
        Record r = rs.next();
        Node n = r.getValue("n");
        System.out.println(n.toString());
    }
}
