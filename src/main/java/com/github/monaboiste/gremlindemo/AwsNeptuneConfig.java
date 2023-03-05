package com.github.monaboiste.gremlindemo;

import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource.traversal;

@Configuration
class AwsNeptuneConfig {

    @Bean
    public Cluster cluster() {
        return Cluster.build()
                .addContactPoint("localhost")
                .port(8182)
                .enableSsl(false)
                .maxConnectionPoolSize(5)
                .maxInProcessPerConnection(1)
                .maxSimultaneousUsagePerConnection(1)
                .minSimultaneousUsagePerConnection(1)
                .create();
    }

    @Bean
    public GraphTraversalSource g(final Cluster cluster) {
        return traversal().withRemote(DriverRemoteConnection.using(cluster));
    }
}
