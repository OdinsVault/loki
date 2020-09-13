package mapper;

import org.yaml.snakeyaml.Yaml;
import org.apache.commons.io.FilenameUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class contains logics for mapping source code.
 * If the source code file provided then that file will be translated.
 * Otherwise all the files with .simply extension in the directory will be translated
 */

@Command(name = "map", description = "Map native language code to english")
public class MapperService implements Runnable{

    private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Option(
        names = {"-l", "--langId"},
        description = "Native Language Id",
        required = true
    )
    String lngId;

    @Option(
        names = {"-src", "--sourceFile"},
        description = "Source Code Path"
    )
    String sourcePath;

    @Option(
        names = {"-dir", "--sourceDirectory"},
        description = "Source Directory"
    )
    String sourceDir;

    @Option(
        names = {"-m", "--mappings"},
        description = "Mappings Directory",
        defaultValue = "mappings/MappingRegistry.yml"
    )
    String mappingsDir;

    public static void main(String[] args) {
        new CommandLine(new MapperService()).execute(args);
    }

    @Override
    public void run() {
        if(this.sourceDir == null && this.sourcePath == null){
            LOGGER.warning("User must provide either source file or directory");
            return;
        }

        if(this.sourcePath != null){
            try {
                this.mapSingleFile();
            } catch (IOException e) {
                LOGGER.warning(e.toString());
            }
            return;
        }

        if(this.sourceDir != null){
            try {
                this.mapDirectory();
            } catch (IOException e) {
                LOGGER.warning(e.toString());
            }
            return;
        }
    }

    private void mapSingleFile() throws IOException {
        Map<String, String> mapping = this.getMapping(this.mappingsDir, this.lngId);
        this.translateSingleFile(mapping, this.sourcePath);
    }

    private void mapDirectory() throws IOException {
        Map<String, String> mapping = this.getMapping(this.mappingsDir, this.lngId);

        // Get all simply files
        File workingDirectory = new File(this.sourceDir);

        File[] simplyFiles = workingDirectory.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File file, String s) {
                return s.endsWith(".simply");
            }
        });

        //translate all files
        for (File file: simplyFiles){
            this.translateSingleFile(mapping, file.getPath());
        }
    }

    private Map<String, String> getMapping(String mappingsDir, String lngId) throws IOException {
        // Get mapping registry
        Map<String, String> mappingRegistry = this.readYamlToMap(
            this.mappingsDir
        );

        // Get mapping file from registry
        String mappingFilePath = mappingRegistry.get(lngId);

        // Get mapping file
        Map<String, String> mapping = this.readYamlToMap(mappingFilePath);

        return mapping;
    }


    /**
     * Read YAML file and deserialize to Map<String, String>
     *
     * @param _filePath YAML file path
     */
    private Map<String, String> readYamlToMap(String _filePath) throws FileNotFoundException {
        Yaml yaml = new Yaml();

        File mappingFile;

        try{
           mappingFile = new File(_filePath);
        }catch (NullPointerException e){
            LOGGER.warning(e.toString());
            throw e;
        }

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(mappingFile);
        } catch (FileNotFoundException e) {
            LOGGER.warning(e.toString());
            throw e;
        }

        Map<String, String> map;

        try{
            map = yaml.load(inputStream);
        }catch (Exception e){
            LOGGER.warning("Invalid Mapping File " + _filePath);
            throw e;
        }

        return map;
    }

    /**
     * Translates single file from native language to English
     * @param _map Keyword Map
     * @param _sourceCodePath Path to the native language code
     */
    private void translateSingleFile(Map<String, String> _map, String _sourceCodePath){
        File nativeSourceCode = new File(this.sourcePath);
        String nativeCode = "";

        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(nativeSourceCode));
            String line = bufferedReader.readLine();

            while(line != null){
                nativeCode += line + System.lineSeparator();
                line = bufferedReader.readLine();
            }

            String targetCode = nativeCode;

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
