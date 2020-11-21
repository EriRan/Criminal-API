package fi.eriran.criminalapi.testcore.deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;

public class GraphQLResponseDeserializer {

    public static <T> T deserialize(GraphQLResponse response, Class<T> targetClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        JsonNode jsonNode = mapper.readTree(response.getRawResponse().getBody());
        return mapper.readValue(findQueryResultNode(jsonNode).toString(), targetClass);
    }

    /**
     * First node is the data node, which in turn contains the query result node. Inside that we have the actual
     * response.
     *
     * {
     *     data : {
     *         query : {
     *             id <-
     *             name <-
     *             appearance <- These are what we want
     *         }
     *     }
     * }
     */
    private static JsonNode findQueryResultNode(JsonNode jsonNode) {
        JsonNode dataNode = getFirstChildNode(jsonNode);
        return getFirstChildNode(dataNode);
    }

    private static JsonNode getFirstChildNode(JsonNode jsonNode) {
        return jsonNode.iterator().next();
    }
}
