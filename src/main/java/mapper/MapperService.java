package mapper;

import org.yaml.snakeyaml.Yaml;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Map;
import java.util.logging.Logger;

public class MapperService {
    private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    /**
     * Map the native lang code to target lang code (English)
     *
     * @param mapping_id id of the mapping (english -> en, spanish -> sp)
     * @param source_file_path Source code file path
     */
    public String map(String mapping_id, String source_file_path) throws FileNotFoundException, IOException {

        // Get mapping registry
        Map<String, String> mappingRegistry = this.readYamlToMap(
            "mappings/MappingRegistry.yml"
        );

        // Get mapping file from registry
        String mappingFilePath = mappingRegistry.get(mapping_id);

        // Get mapping file
        Map<String, String> mapping = this.readYamlToMap(mappingFilePath);

        this.test_translate(mapping, "");

        return null;
    }


    /**
     * Read YAML file and deserialize to Map<String, String>
     *
     * @param filepath YAML file path
     */
    private Map<String, String> readYamlToMap(String filepath) throws IOException, FileNotFoundException {
        Yaml yaml = new Yaml();

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(
                    new File(filepath)
            );
        } catch (FileNotFoundException e) {
            LOGGER.warning(filepath + " Not Found");
            throw new FileNotFoundException(filepath + " Not Found");
        }

        Map<String, String> map;

        try{
            map = yaml.load(inputStream);
        }catch (Exception e){
            LOGGER.warning("Invalid Mapping File " + filepath);
            throw new IOException("Invalid Mapping File " + filepath);
        }

        return map;
    }

    /** NOT COMPLETED. BUT I JUST WANT TO SLEEP
     * Translate the native language code into english
     * @param map Keyword mapping
     * @param source_code native language code file path
     * */
    private String translate(Map<String, String> map, String source_code){

        File sourceCode = new File(source_code);
        String oldContent = "";
        BufferedReader bufferedReader = null;
        FileWriter fileWriter = null;

        try{
            bufferedReader = new BufferedReader(new FileReader(sourceCode));
            String line = bufferedReader.readLine();

            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();

                line = bufferedReader.readLine();
            }

            String newContent = "";

            for(String key: map.keySet()){
                newContent = oldContent.replaceAll(key, map.get(key));
            }

            String fileNameWithOutExt = FilenameUtils.removeExtension(source_code);

            fileWriter = new FileWriter(new File(source_code + "target.txt"));

            fileWriter.write(newContent);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private void test_translate(Map<String, String> map, String source_code){
        String code = "this is a සරල  code to test පරිවර්තනය";
        String oldCode = code;

        for(String key: map.keySet()){
            code = code.replaceAll(map.get(key), key);
        }

        System.out.println(oldCode);
        System.out.println(code);
    }
}
