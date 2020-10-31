package fi.eriran.graphqltraining.main;

import com.google.common.io.Resources;
import fi.eriran.graphqltraining.main.resolver.ChargeResolver;
import fi.eriran.graphqltraining.main.resolver.Mutation;
import fi.eriran.graphqltraining.main.resolver.Query;
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
                .resolvers(query, mutation, chargeResolver)
                .build()
                .makeExecutableSchema();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}
