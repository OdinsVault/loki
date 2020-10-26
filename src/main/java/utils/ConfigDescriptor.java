package utils;

public class ConfigDescriptor {

    // sinhala, tamil
    private String target;

    // sinhala, tamil
    private String source;

    // java, python
    private String lng;

    // file to be converted
    private String sourceFile;

    public String getTargetMotherTongueId() {
        return target;
    }

    public void setTargetMotherTongueId(String targetMotherTongueId) {
        this.target = targetMotherTongueId;
    }

    public String getSourceMotherTongueId() {
        return source;
    }

    public void setSourceMotherTongueId(String sourceMotherTongueId) {
        this.source = sourceMotherTongueId;
    }

    public String getProgrammingLanguage() {
        return lng;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.lng = programmingLanguage;
    }

    public String getSourceFilePath() {
        return sourceFile;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFile = sourceFilePath;
    }
}
