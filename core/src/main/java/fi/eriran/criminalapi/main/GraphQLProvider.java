package fi.eriran.criminalapi.main;

import com.google.common.io.Resources;
import fi.eriran.criminalapi.configuration.ScalarConfiguration;
import fi.eriran.criminalapi.main.resolver.ChargeResolver;
import fi.eriran.criminalapi.main.resolver.Mutation;
import fi.eriran.criminalapi.main.resolver.Query;
import fi.eriran.criminalapi.main.resolver.SightingResolver;
import graphql.GraphQL;
import graphql.kickstart.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    private Query query;
    @Autowired
    private Mutation mutation;
    @Autowired
    private ChargeResolver chargeResolver;
    @Autowired
    private SightingResolver sightingResolver;

    @Autowired
    private ScalarConfiguration scalarConfiguration;

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String schemaContent = Resources.toString(url, StandardCharsets.UTF_8);
        this.graphQL = GraphQL.newGraphQL(
                buildSchema(schemaContent)
        ).build();
    }

    private GraphQLSchema buildSchema(String content) {
        return SchemaParser.newParser()
                .schemaString(content)
                .resolvers(query, mutation, chargeResolver, sightingResolver)
                .scalars(scalarConfiguration.dateTime())
                .build()
                .makeExecutableSchema();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}
