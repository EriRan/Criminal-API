package fi.eriran.criminalapi;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import fi.eriran.criminalapi.configuration.ScalarConfiguration;
import fi.eriran.criminalapi.main.dao.ChargeDao;
import fi.eriran.criminalapi.main.dao.CriminalDao;
import fi.eriran.criminalapi.main.dao.SightingDao;
import fi.eriran.criminalapi.main.pojo.Criminal;
import fi.eriran.criminalapi.testutil.filepath.QueryFilePathProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * GraphQLTest does not seem to work when custom scalars have been added. It is unable to find them from the
 * configuration class I made.
 */
@Disabled
@GraphQLTest
class CriminalGraphQLTest {

    @MockBean
    private CriminalDao criminalDao;
    @MockBean
    private ChargeDao chargeDao;
    @MockBean
    private SightingDao sightingDao;
    @MockBean
    private ScalarConfiguration scalarConfiguration;

    @Autowired
    private GraphQLTestTemplate testTemplate;

    @Test
    void getOnlyCriminal() throws IOException {
        when(criminalDao.get(any())).thenReturn(new Criminal());

        GraphQLResponse response = testTemplate.postForResource(
                new QueryFilePathProvider().provide("criminal")
        );
        assertNotNull(response);
    }
}
