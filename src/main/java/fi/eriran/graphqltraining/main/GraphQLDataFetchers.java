package fi.eriran.graphqltraining.main;

import fi.eriran.graphqltraining.main.dao.ChargeDao;
import fi.eriran.graphqltraining.main.dao.CriminalDao;
import fi.eriran.graphqltraining.main.pojo.Charge;
import fi.eriran.graphqltraining.main.pojo.Criminal;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class GraphQLDataFetchers {

    @Autowired
    private CriminalDao criminalDao;

    @Autowired
    private ChargeDao chargeDao;

    public DataFetcher<Criminal> getCriminalById() {
        return dataFetchingEnvironment -> criminalDao
                .getCriminal(Integer.parseInt(dataFetchingEnvironment.getArgument("id")));
    }

    public DataFetcher<Collection<Charge>> getCriminalCharge() {
        return dataFetchingEnvironment -> {
            Criminal source = dataFetchingEnvironment.getSource();
            return chargeDao.getCharges(source.getId());
        };
    }
}
