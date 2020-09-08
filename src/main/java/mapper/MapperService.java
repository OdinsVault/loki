package mapper;

import org.yaml.snakeyaml.Yaml;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class contains logics for mapping source code.
 * If the source code file provided then that file will be translated.
 * Otherwise all the files with .simply extension in the directory will be translated
 */
public class MapperService {

    private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Map the native lang code to target lang code (English)
     *
     * @param _mappingId id of the mapping (english -> en, spanish -> sp)
     * @param _sourceCodePath Source code file path
     */
    public void map(String _mappingId, String _sourceCodePath) throws FileNotFoundException, IOException {

        // Get mapping registry
        Map<String, String> mappingRegistry = this.readYamlToMap(
            "mappings/MappingRegistry.yml"
        );

        // Get mapping file from registry
        String mappingFilePath = mappingRegistry.get(_mappingId);

        // Get mapping file
        Map<String, String> mapping = this.readYamlToMap(mappingFilePath);

        translate(mapping, _sourceCodePath);
    }


    /**
     * Read YAML file and deserialize to Map<String, String>
     *
     * @param _filePath YAML file path
     */
    private Map<String, String> readYamlToMap(String _filePath) throws IOException, FileNotFoundException {
        Yaml yaml = new Yaml();

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(
                    new File(_filePath)
            );
        } catch (FileNotFoundException e) {
            LOGGER.warning(_filePath + " Not Found");
            throw new FileNotFoundException(_filePath + " Not Found");
        }

        Map<String, String> map;

        try{
            map = yaml.load(inputStream);
        }catch (Exception e){
            LOGGER.warning("Invalid Mapping File " + _filePath);
            throw new IOException("Invalid Mapping File " + _filePath);
        }

        return map;
    }

    /**
     * Translate the native language code into english.
     * @param _map Keyword mapping.
     * @param _sourceCodePath native language code file path.
     * */
    private void translate(Map<String, String> _map, String _sourceCodePath){

        if(_sourceCodePath != null){
            translateSingleFile(_map, _sourceCodePath);
        }else{
            translateAllFiles(_map);
        }
    }

    /**
     * Translates single file from native language to English
     * @param _map Keyword Map
     * @param _sourceCodePath Path to the native language code
     */
    private void translateSingleFile(Map<String, String> _map, String _sourceCodePath){
        File nativeSourceCode = new File(_sourceCodePath);
        String nativeCode = new String("");

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
                System.out.println(key + ":" + _map.get(key));
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

    /**
     * Tanslate all simply files from native language to English
     * @param _map Keyword Map
     */
    private void translateAllFiles(Map<String, String> _map){
        // Get all simply files
        File workingDirecotry = new File(System.getProperty(("user.dir")));

        File[] simplyFiles = workingDirecotry.listFiles(new FilenameFilter(){
            @Override
            public boolean accept(File file, String s) {
                return s.endsWith(".simply");
            }
        });

        //translate all files
        for (File file: simplyFiles){
            this.translateSingleFile(_map, file.getPath());
        }
    }
}
