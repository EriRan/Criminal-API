package fi.eriran.criminalapi.testutil.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import fi.eriran.criminalapi.main.pojo.Criminal;

public class GraphQLResponseDeserializer {

    public static <T> T deserialize(GraphQLResponse response, String queryName, Class<T> targetClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.getRawResponse().getBody());
        JsonNode responseData = jsonNode.get("data").get(queryName);

        return mapper.readValue(responseData.toString(), targetClass);
    }
}
