package fi.eriran.criminalapi.testcore.query;

import com.graphql.spring.boot.test.GraphQLTestTemplate;

/**
 * Wrap a {@link String} into curly brackets, making the query usable in {@link GraphQLTestTemplate}
 */
public class GraphQLQueryBuilderJsonWrapper {

    private GraphQLQueryBuilderJsonWrapper() {
    }

    public static String wrap(String query) throws IllegalArgumentException {
        return "{" + query + "}";
    }
}
