package fi.eriran.graphqltraining.main.resolver;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AdditionalScalars {

    public List<GraphQLScalarType> provideScalars() {
        return Collections.singletonList(ExtendedScalars.DateTime);
    }
}
