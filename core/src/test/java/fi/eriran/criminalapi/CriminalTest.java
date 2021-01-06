package fi.eriran.criminalapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import fi.eriran.criminalapi.main.dao.ChargeDao;
import fi.eriran.criminalapi.main.dao.CriminalDao;
import fi.eriran.criminalapi.main.dao.SightingDao;
import fi.eriran.criminalapi.main.pojo.Charge;
import fi.eriran.criminalapi.main.pojo.Criminal;
import fi.eriran.criminalapi.main.pojo.Sighting;
import fi.eriran.criminalapi.testcore.annotation.CriminalApiGraphQLTest;
import fi.eriran.criminalapi.testcore.deserialization.GraphQLResponseDeserializer;
import fi.eriran.criminalapi.testcore.testdata.ChargeTestObjectFactory;
import fi.eriran.criminalapi.testcore.testdata.CriminalTestObjectFactory;
import fi.eriran.criminalapi.testcore.testdata.SightingTestObjectFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder.object;
import static com.github.k0kubun.builder.query.graphql.GraphQLQueryBuilder.query;
import static fi.eriran.criminalapi.testcore.query.GraphQLQueryBuilderJsonWrapper.wrap;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Ensure that queries and the response deserialization works correctly for Criminal related objects
 */
@CriminalApiGraphQLTest
class CriminalTest {

    @MockBean
    private CriminalDao criminalDao;
    @MockBean
    private ChargeDao chargeDao;
    @MockBean
    private SightingDao sightingDao;

    @Autowired
    private GraphQLTestTemplate testTemplate;

    @BeforeEach
    public void initMocks() {
        when(criminalDao.get(anyInt()))
                .thenReturn(CriminalTestObjectFactory.create(1));
        when(chargeDao.getCharges(anyInt()))
                .thenReturn(
                        Collections.singletonList(
                                ChargeTestObjectFactory.create(1)
                        )

                );
        when(sightingDao.getSightings(anyInt()))
                .thenReturn(
                        Collections.singletonList(
                                SightingTestObjectFactory.create(1)
                        )

                );
    }

    @Test
    void criminalVariablesFetched() throws JsonProcessingException {
        Map<String, Object> criminalParams = new HashMap<>();
        criminalParams.put("id", "1");
        GraphQLResponse response = testTemplate
                .postMultipart(
                        wrap(
                                query()
                                        .object("criminalById", criminalParams, object()
                                                .field("id")
                                                .field("name")
                                                .field("appearance")
                                                .build())
                                        .build()
                        ),
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
                        wrap(
                                query()
                                        .object("criminalById", criminalParams, object()
                                                .field("name")
                                                .build())
                                        .build()
                        ),
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
                        wrap(
                                query()
                                        .object("criminalById", criminalParams, object()
                                                .field("id")
                                                .field("name")
                                                .field("appearance")
                                                .object("charges", object()
                                                        .field("id")
                                                        .field("description")
                                                        .build())
                                                .build())
                                        .build()
                        ),
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
                        wrap(
                                query()
                                        .object("criminalById", criminalParams, object()
                                                .field("id")
                                                .field("name")
                                                .field("appearance")
                                                .object("sightings", object()
                                                        .field("id")
                                                        .field("description")
                                                        .field("timeOfSighting")
                                                        .field("address")
                                                        .field("area")
                                                        .build())
                                                .build())
                                        .build()
                        ),
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
