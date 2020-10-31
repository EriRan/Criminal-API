package fi.eriran.graphqltraining.main.resolver;

import fi.eriran.graphqltraining.main.dao.ChargeDao;
import fi.eriran.graphqltraining.main.pojo.Charge;
import fi.eriran.graphqltraining.main.pojo.Criminal;
import graphql.kickstart.tools.GraphQLResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class ChargeResolver implements GraphQLResolver<Criminal> {

    @Autowired
    private ChargeDao chargeDao;

    private final Logger logger = LoggerFactory.getLogger(ChargeResolver.class);

    public Collection<Charge> getCharges(Criminal criminal) {
        if (criminal == null) {
            return Collections.emptyList();
        }
        logger.debug("charges for criminal id: {}", criminal.getId());
        Collection<Charge> charges = chargeDao.getCharges(criminal.getId());
        logger.debug("Found {} charges", charges.size());
        return charges;
    }
}
