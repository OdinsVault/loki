package mapper.utils;

public class ConfigDescriptor {

    private String targetLangId;

    private String sourceLangId;

    private String sourceFilePath;

    // validate target language id
    public void setTargetLangId(String targetLangId) {
        this.targetLangId = targetLangId;
    }

    // validate source language id
    public void setSourceLangId(String sourceLangId) {
        this.sourceLangId = sourceLangId;
    }

    // validate source file path
    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getTargetLangId() {
        return targetLangId;
    }

    public String getSourceLangId() {
        return sourceLangId;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }
}
