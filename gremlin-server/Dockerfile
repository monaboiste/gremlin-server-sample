FROM tinkerpop/gremlin-server:3.6

COPY ./gremlin-conf.yaml /opt/gremlin-server/conf/gremlin-conf.yaml
COPY ./tinkergraph-custom.properties /opt/gremlin-server/conf/tinkergraph-custom.properties

EXPOSE 8182

CMD ["conf/gremlin-conf.yaml"]