package fi.eriran.criminalapi;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import fi.eriran.criminalapi.configuration.ScalarConfiguration;
import fi.eriran.criminalapi.main.dao.ChargeDao;
import fi.eriran.criminalapi.main.dao.CriminalDao;
import fi.eriran.criminalapi.main.dao.SightingDao;
import fi.eriran.criminalapi.main.pojo.Criminal;
import fi.eriran.criminalapi.testcore.annotation.CriminalApiGraphQLTest;
import fi.eriran.criminalapi.testcore.util.filepath.QueryFilePathProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@CriminalApiGraphQLTest
class CriminalGraphQLTest {

    @MockBean
    private CriminalDao criminalDao;
    @MockBean
    private ChargeDao chargeDao;
    @MockBean
    private SightingDao sightingDao;

    @Autowired
    private GraphQLTestTemplate testTemplate;

    @Test
    void getOnlyCriminal() throws IOException {
        when(criminalDao.get(any())).thenReturn(new Criminal());
        when(sightingDao.getSightings(any())).thenReturn(new ArrayList<>());
        when(chargeDao.getCharges(any())).thenReturn(new ArrayList<>());

        GraphQLResponse response = testTemplate.postForResource(
                new QueryFilePathProvider().provide("criminal")
        );
        assertNotNull(response);
    }
}
