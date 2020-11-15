package fi.eriran.criminalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.k0kubun.builder.query.graphql.GraphQL;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import fi.eriran.criminalapi.main.pojo.Criminal;
import fi.eriran.criminalapi.testutil.annotation.CriminalApiSpringBootTest;
import fi.eriran.criminalapi.testutil.query.GraphQLQueryBuilderProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CriminalApiSpringBootTest
class CriminalTest {

    @Autowired
    private GraphQLTestTemplate testTemplate;

    @Test
    void requestedVariablesFetched() throws JsonProcessingException {
        Map<String, Object> criminalParams = new HashMap<>();
        criminalParams.put("id", "1");


        GraphQLResponse response = testTemplate
                .postMultipart(
                        GraphQLQueryBuilderProvider.createQuery()
                                .object("criminalById", criminalParams, GraphQL.createObjectBuilder()
                                        .field("id")
                                        .field("name")
                                        .field("appearance")
                                        .build())
                                .build(),
                        "{}");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.getRawResponse().getBody());
        JsonNode responseData = jsonNode.get("data").get("criminalById");

        Criminal criminal = mapper.readValue(responseData.toString(), Criminal.class);

        assertNotNull(criminal);
        assertNotNull(criminal.getId());
        assertNotNull(criminal.getName());
        assertNotNull(criminal.getAppearance());
    }
}
