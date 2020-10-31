package fi.eriran.graphqltraining.main.resolver;

import fi.eriran.graphqltraining.main.dao.CriminalDao;
import fi.eriran.graphqltraining.main.pojo.Criminal;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private CriminalDao criminalDao;

    public Criminal getCriminalById(Integer id) {
        return criminalDao.get(id);
    }

}
