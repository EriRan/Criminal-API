package fi.eriran.criminalapi;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import fi.eriran.criminalapi.testutil.QueryFilePathProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:hikari.properties"
)
class CriminalQueryTest {

    @Autowired
    private GraphQLTestTemplate testTemplate;

    @Test
    void runQuery() throws IOException {
        GraphQLResponse response = testTemplate
                .postForResource(new QueryFilePathProvider().provide("fullCriminal"));
        assertNotNull(response);
    }
}
