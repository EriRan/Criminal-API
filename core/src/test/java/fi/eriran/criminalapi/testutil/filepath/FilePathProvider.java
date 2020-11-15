package fi.eriran.criminalapi.testutil.filepath;

abstract class FilePathProvider {

    public String provideFile(String folderPath, String fileName) {
        return String.format(folderPath, fileName);
    }
}
