package mapper;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MapperService {

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

        return mapping.get("for");
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
            // TODO: Use logs instead Console out
            System.out.println(filepath + " Not Found");
            throw new FileNotFoundException(filepath + " Not Found");
        }

        Map<String, String> map;

        try{
            map = yaml.load(inputStream);
        }catch (Exception e){
            // TODO: Use logs instead Console out
            System.out.println("Invalid Mapping File " + filepath);
            throw new IOException("Invalid Mapping File " + filepath);
        }

        return map;
    }
}
