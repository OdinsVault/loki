package utils;

public class ConfigDescriptor {

    // sinhala, tamil
    private String targetMotherTongueId;

    // sinhala, tamil
    private String sourceMotherTongueId;

    // java, python
    private String programmingLanguage;

    // file to be converted
    private String sourceFilePath;

    public String getTargetMotherTongueId() {
        return targetMotherTongueId;
    }

    public void setTargetMotherTongueId(String targetMotherTongueId) {
        this.targetMotherTongueId = targetMotherTongueId;
    }

    public String getSourceMotherTongueId() {
        return sourceMotherTongueId;
    }

    public void setSourceMotherTongueId(String sourceMotherTongueId) {
        this.sourceMotherTongueId = sourceMotherTongueId;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }
}
