package fi.eriran.criminalapi.testcore.util.filepath;

public class QueryFilePathProvider extends FilePathProvider {

    private static final String FILEPATH = "graphql/query/%s.query";

    public String provide(String fileName) {
        return provideFile(FILEPATH, fileName);
    }
}
