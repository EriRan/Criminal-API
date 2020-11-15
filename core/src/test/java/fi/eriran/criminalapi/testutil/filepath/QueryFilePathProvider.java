package fi.eriran.criminalapi.testutil.filepath;

public class QueryFilePathProvider extends FilePathProvider{

    private static final String FILEPATH = "graphql/query/%s.query";

    public String provide(String fileName) {
        return provideFile(FILEPATH, fileName);
    }
}
