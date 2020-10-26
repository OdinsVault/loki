package mapper;

import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import utils.MapUtils;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

@CommandLine.Command(name = "map", description = "Map native language code to english")
public class Mapper implements Runnable{

    private final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Option(
        names = {"-n", "--nativeLangId"},
        description = "Native Language Id",
        required = true
    )
    private String lngId;

    @Option(
        names = {"-src", "--sourceFile"},
        description = "Source Code Path",
        required = true
    )
    private String srcFilePath;

    public static void main(String[] args) {
        new CommandLine(new Mapper()).execute(args);
    }

    @Override
    public void run() {
        Map<String, String> map;
        try {
            map = MapUtils.getMappingById(this.lngId);
            this.mapSingleFile(map, this.srcFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Translates single file from native language to English
     * @param _map Keyword Map
     * @param _sourceCodePath Path to the native language code
     */
    private void mapSingleFile(Map<String, String> _map, String _sourceCodePath){
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
            nativeCodeFileBaseName += "_target.simply";

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
