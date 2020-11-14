package fi.eriran.criminalapi.testutil;

public class QueryFilePathProvider {

    private static final String FILEPATH = "graphql/query/%s.query";

    public String provide(String fileName) {
        return String.format(FILEPATH, fileName);
    }
}
