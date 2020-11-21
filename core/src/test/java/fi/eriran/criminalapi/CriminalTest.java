package fi.eriran.criminalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.k0kubun.builder.query.graphql.GraphQL;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import fi.eriran.criminalapi.main.pojo.Criminal;
import fi.eriran.criminalapi.testutil.annotation.CriminalApiSpringBootTest;
import fi.eriran.criminalapi.testutil.deserialization.GraphQLResponseDeserializer;
import fi.eriran.criminalapi.testutil.query.GraphQLQueryBuilderProvider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Ensure that queries and the response deserialization works correctly for Criminal related objects
 */
@CriminalApiSpringBootTest
class CriminalTest {

    @Autowired
    private GraphQLTestTemplate testTemplate;

    @Test
    void criminalVariablesFetched() throws JsonProcessingException {
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
        Criminal criminal = GraphQLResponseDeserializer.deserialize(response, Criminal.class);

        assertNotNull(criminal);
        assertNotNull(criminal.getId());
        assertNotNull(criminal.getName());
        assertNotNull(criminal.getAppearance());

        assertTrue(CollectionUtils.isEmpty(criminal.getCharges()));
        assertTrue(CollectionUtils.isEmpty(criminal.getSightings()));
    }

    @Test
    void notRequestedVariablesAreNotFetched() throws JsonProcessingException {
        Map<String, Object> criminalParams = new HashMap<>();
        criminalParams.put("id", "1");

        //Just give me the name of the man!!!
        GraphQLResponse response = testTemplate
                .postMultipart(
                        GraphQLQueryBuilderProvider.createQuery()
                                .object("criminalById", criminalParams, GraphQL.createObjectBuilder()
                                        .field("name")
                                        .build())
                                .build(),
                        "{}");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Criminal criminal = GraphQLResponseDeserializer.deserialize(response, Criminal.class);

        assertNotNull(criminal);
        assertNull(criminal.getId());
        assertNotNull(criminal.getName());
        assertNull(criminal.getAppearance());

        assertTrue(CollectionUtils.isEmpty(criminal.getCharges()));
        assertTrue(CollectionUtils.isEmpty(criminal.getSightings()));
    }

    @Test
    void chargesFetched() throws JsonProcessingException {
        Map<String, Object> criminalParams = new HashMap<>();
        criminalParams.put("id", "1");

        GraphQLResponse response = testTemplate
                .postMultipart(
                        GraphQLQueryBuilderProvider.createQuery()
                                .object("criminalById", criminalParams, GraphQL.createObjectBuilder()
                                        .field("id")
                                        .field("name")
                                        .field("appearance")
                                        .object("charges", GraphQL.createObjectBuilder()
                                                .field("id")
                                                .field("description")
                                                .build())
                                        .build())
                                .build(),
                        "{}");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Criminal criminal = GraphQLResponseDeserializer.deserialize(response, Criminal.class);

        assertNotNull(criminal);
        assertTrue(CollectionUtils.isEmpty(criminal.getSightings()));
        assertFalse(CollectionUtils.isEmpty(criminal.getCharges()));
        criminal.getCharges().forEach(charge -> {
            assertNotNull(charge.getId());
            assertFalse(StringUtils.isBlank(charge.getDescription()));
        });
    }

    @Test
    void sightingsFetched() throws JsonProcessingException {
        Map<String, Object> criminalParams = new HashMap<>();
        criminalParams.put("id", "1");

        GraphQLResponse response = testTemplate
                .postMultipart(
                        GraphQLQueryBuilderProvider.createQuery()
                                .object("criminalById", criminalParams, GraphQL.createObjectBuilder()
                                        .field("id")
                                        .field("name")
                                        .field("appearance")
                                        .object("sightings", GraphQL.createObjectBuilder()
                                                .field("id")
                                                .field("description")
                                                .field("timeOfSighting")
                                                .field("address")
                                                .field("area")
                                                .build())
                                        .build())
                                .build(),
                        "{}");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Criminal criminal = GraphQLResponseDeserializer.deserialize(response, Criminal.class);

        assertNotNull(criminal);
        assertTrue(CollectionUtils.isEmpty(criminal.getCharges()));
        assertFalse(CollectionUtils.isEmpty(criminal.getSightings()));
        criminal.getSightings().forEach(sighting -> {
            assertNotNull(sighting.getId());
            assertFalse(StringUtils.isBlank(sighting.getDescription()));
            assertFalse(StringUtils.isBlank(sighting.getAddress()));
            assertFalse(StringUtils.isBlank(sighting.getArea()));
            assertNotNull(sighting.getTimeOfSighting());
        });
    }
}
