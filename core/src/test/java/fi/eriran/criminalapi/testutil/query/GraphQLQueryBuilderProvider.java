package fi.eriran.criminalapi.testutil.query;

import com.github.k0kubun.builder.query.graphql.GraphQL;
import com.github.k0kubun.builder.query.graphql.builder.GraphQLObjectBuilder;
import com.github.k0kubun.builder.query.graphql.builder.GraphQLQueryBuilder;

/**
 * Wraps k0kubun's GraphQL Query builder in another name so that it doesn't conflict with java-graphql's GraphQL
 * class. Also includes a few fixes to make the query usable.
 */
public class GraphQLQueryBuilderProvider {

    public static GraphQLQueryBuilder createQuery() {
        return new GraphQLQueryBuilderExtended();
    }

    public static GraphQLObjectBuilder createObject() {
        return GraphQL.createObjectBuilder();
    }
}
