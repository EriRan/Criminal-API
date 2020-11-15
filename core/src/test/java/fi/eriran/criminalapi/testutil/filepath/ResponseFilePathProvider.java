package fi.eriran.criminalapi.testutil.filepath;

public class ResponseFilePathProvider extends FilePathProvider {

    private static final String FILEPATH = "graphql/response/%s.json";

    public String provide(String fileName) {
        return provideFile(FILEPATH, fileName);
    }
}
