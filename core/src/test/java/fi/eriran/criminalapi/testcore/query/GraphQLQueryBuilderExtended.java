package fi.eriran.criminalapi.testcore.query;

import com.github.k0kubun.builder.query.graphql.builder.GraphQLQueryBuilder;
import com.github.k0kubun.builder.query.graphql.model.GraphQLField;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Identical to the parent apart from appending curly braces to the start and end so that the query is valid json
 */
public class GraphQLQueryBuilderExtended extends GraphQLQueryBuilder {

    @Override
    public String build() throws IllegalArgumentException {
        StringBuilder builder = new StringBuilder();

        for (GraphQLField field : getFieldsWithReflection()) {
            builder.append(field.indentRender(0));
        }

        return "{" + builder.toString() + "}";
    }

    private List<GraphQLField> getFieldsWithReflection() {
        try {

            Field field = FieldUtils.getField(GraphQLQueryBuilder.class, "fields", true);
            return (List<GraphQLField>) field.get(this);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unable to access fields with reflection", e);
        }
    }
}
