package localizer;

import utils.MapUtils;
import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

@CommandLine.Command(name = "localize", description = "Localize Simply code to native language")
public class Localizer implements Runnable{

    private final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Option(
        names = {"-t", "--targetLngId"},
        description = "Target language ID",
        required = true
    )
    private String targetLang;

    @Option(
        names = {"-src", "--sourceFile"},
        description = "Source simply code",
        required = true
    )
    private String srcFilePath;

    @Override
    public void run() {
        Map<String, String> map;
        try{
            map = MapUtils.getMappingById(this.targetLang, true);
            this.localizeSingleFile(map, this.srcFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Translates single file from native language to English
     * @param _map Keyword Map
     * @param _sourceCodePath Path to the native language code
     */
    private void localizeSingleFile(Map<String, String> _map, String _sourceCodePath){
        File translatedSourceCode = new File(this.srcFilePath);
        StringBuilder nativeCode = new StringBuilder();

        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(translatedSourceCode));
            String line = bufferedReader.readLine();

            while(line != null){
                nativeCode.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }

            String targetCode = nativeCode.toString();

            for(String key: _map.keySet()){
                targetCode = targetCode.replaceAll(_map.get(key), key);
            }

            String nativeCodeFileBaseName = FilenameUtils.removeExtension(_sourceCodePath);
            nativeCodeFileBaseName += "_original.simply";

            FileWriter fileWriter = new FileWriter(nativeCodeFileBaseName);

            fileWriter.write(targetCode);

            bufferedReader.close();
            fileWriter.close();

        } catch (FileNotFoundException e) {
            LOGGER.warning("Source code file not found " + _sourceCodePath);
        } catch (IOException e) {
            LOGGER.warning("Error in file " + _sourceCodePath);
        }

    }

}
