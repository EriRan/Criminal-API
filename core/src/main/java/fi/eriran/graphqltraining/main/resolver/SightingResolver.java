package fi.eriran.graphqltraining.main.resolver;

import fi.eriran.graphqltraining.main.dao.SightingDao;
import fi.eriran.graphqltraining.main.pojo.Criminal;
import fi.eriran.graphqltraining.main.pojo.Sighting;
import graphql.kickstart.tools.GraphQLResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

@Component
public class SightingResolver implements GraphQLResolver<Sighting> {

    @Resource
    private SightingDao sightingDao;

    private final Logger logger = LoggerFactory.getLogger(SightingResolver.class);

    public Collection<Sighting> getSightings(Criminal criminal) {
        if (criminal == null) {
            return Collections.emptyList();
        }
        logger.debug("Sightings for criminal id: {}", criminal.getId());
        Collection<Sighting> sightings = sightingDao.getSightings(criminal.getId());
        logger.debug("Found {} sightings", sightings.size());
        return sightings;
    }
}
