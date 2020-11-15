package fi.eriran.criminalapi;

import com.github.k0kubun.builder.query.graphql.GraphQL;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import fi.eriran.criminalapi.testutil.annotation.CriminalApiSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@CriminalApiSpringBootTest
class CriminalTest {

    @Autowired
    private GraphQLTestTemplate testTemplate;

    @Test
    void requestedVariablesFetched() {
        Map<String, Object> criminalParams = new HashMap<>();
        criminalParams.put("id", "1");


        GraphQLResponse response = testTemplate
                .postMultipart("{" +
                        GraphQL.createQueryBuilder()
                                .object("criminalById", criminalParams, GraphQL.createObjectBuilder()
                                        .field("id")
                                        .field("name")
                                        .field("appearance")
                                        .build())
                                .build() + "}",
                        "{}");
        assertNotNull(response);
    }
}
