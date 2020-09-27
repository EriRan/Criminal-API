package fi.eriran.graphqltraining.main;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class GraphQLDataFetchers {

    private static List<Map<String, String>> criminals = Arrays.asList(
            ImmutableMap.of("id", "1",
                    "name", "Osama bin Laden",
                    "appearance", "Long black beard",
                    "chargeId", "1"),
            ImmutableMap.of("id", "1",
                    "name", "Slick Joe",
                    "appearance", "Handsome face, white mohawk, red shirt with a text \"Ouch!\" on it",
                    "chargeId", "2"),
            ImmutableMap.of("id", "1",
                    "name", "Robert von Lomboc",
                    "appearance", "Slim stature. Always wearing white English judge wig",
                    "chargeId", "3")
    );

    private static List<Map<String, String>> criminalCharges = Arrays.asList(
            ImmutableMap.of("id", "1",
                    "description", "Terrorism"),
            ImmutableMap.of("id", "1",
                    "description", "Being overly cool"),
            ImmutableMap.of("id", "1",
                    "description", "Terrorism")
    );

    public DataFetcher getCriminalById() {
        return dataFetchingEnvironment -> {
            String criminalId = dataFetchingEnvironment.getArgument("id");
            return criminals
                    .stream()
                    .filter(book -> book.get("id").equals(criminalId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getCriminalCharge() {
        return dataFetchingEnvironment -> {
            Map<String,String> criminal = dataFetchingEnvironment.getSource();
            String chargeId = criminal.get("chargeId");
            return criminalCharges
                    .stream()
                    .filter(criminalCharge -> criminalCharge.get("id").equals(chargeId))
                    .findFirst()
                    .orElse(null);
        };
    }
}
