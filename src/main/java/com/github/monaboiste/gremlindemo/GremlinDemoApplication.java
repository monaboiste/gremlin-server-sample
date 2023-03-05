package com.github.monaboiste.gremlindemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__;
import org.apache.tinkerpop.gremlin.structure.T;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@SpringBootApplication
public class GremlinDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GremlinDemoApplication.class, args);
	}

	@Slf4j
	@Configuration
	@RequiredArgsConstructor
	class Router {

		private final GraphTraversalSource g;

		@Bean
		RouterFunction<ServerResponse> route() {
			return RouterFunctions.route(POST("/topology"),
					request -> {
						buildRouteTopology();
						return ServerResponse.accepted().build();
					});
		}

		private void buildRouteTopology() {
			g.addV("Station")
					.property(T.id, "zielona-gora-glowna")
					.property("name", "Zielona Góra Główna")
					.iterate();
			g.addV("Station")
					.property(T.id, "sulechow")
					.property("name", "Sulechów")
					.iterate();
			g.addV("Station")
					.property(T.id, "zbaszynek")
					.property("name", "Zbąszynek")
					.iterate();
			g.addV("Station")
					.property(T.id, "swiebodzin")
					.property("name", "Świebodzin")
					.iterate();
			g.addV("Station")
					.property(T.id, "miedzyrzecz")
					.property("name", "Międzyrzecz")
					.iterate();
			g.addV("Station")
					.property(T.id, "poznan-glowny")
					.property("name", "Poznań Główny")
					.iterate();
			g.addV("Station")
					.property(T.id, "szczecin-glowny")
					.property("name", "Szczecin Główny")
					.iterate();

			g.addE("route")
					.from(__.V("zielona-gora-glowna")).to(__.V("sulechow"))
					.property("distance", 500)
					.iterate();

			g.addE("route")
					.from(__.V("sulechow")).to(__.V("zbaszynek"))
					.property("distance", 50)
					.iterate();

			g.addE("route")
					.from(__.V("zbaszynek")).to(__.V("swiebodzin"))
					.property("distance", 50)
					.iterate();

			g.addE("route")
					.from(__.V("swiebodzin")).to(__.V("miedzyrzecz"))
					.property("distance", 50)
					.iterate();
			g.addE("route")
					.from(__.V("zbaszynek")).to(__.V("poznan-glowny"))
					.property("distance", 50)
					.iterate();

			g.addE("route")
					.from(__.V("miedzyrzecz")).to(__.V("poznan-glowny"))
					.property("distance", 50)
					.iterate();

			g.addE("route")
					.from(__.V("poznan-glowny")).to(__.V("szczecin-glowny"))
					.property("distance", 50)
					.iterate();

			var t = g.V().limit(25).elementMap();
			t.forEachRemaining(e ->  log.info(t.toList().toString()));
		}
	}

}
