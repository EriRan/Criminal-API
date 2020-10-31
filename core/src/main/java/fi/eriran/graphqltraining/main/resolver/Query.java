package fi.eriran.graphqltraining.main.resolver;

import fi.eriran.graphqltraining.main.dao.CriminalDao;
import fi.eriran.graphqltraining.main.pojo.Criminal;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Query implements GraphQLQueryResolver {

    @Resource
    private CriminalDao criminalDao;

    public Criminal getCriminalById(Integer id) {
        return criminalDao.get(id);
    }

}
