package mapper.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Logger;

public class YamlUtils {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Read YAML file and deserialize to Map<String, String>
     *
     * @param _filePath YAML file path
     */
    public static Map<String, String> yamlToMap(String _filePath) throws FileNotFoundException {
        Yaml yaml = new Yaml();

        File mappingFile;

        try{
            mappingFile = new File(_filePath);
        }catch (NullPointerException e){
            LOGGER.warning(e.toString());
            throw e;
        }

        InputStream inputStream;

        try {
            inputStream = new FileInputStream(mappingFile);
        } catch (FileNotFoundException e) {
            LOGGER.warning(e.toString());
            throw e;
        }

        Map<String, String> map;

        try{
            map = yaml.load(inputStream);
        }catch (YAMLException e){
            LOGGER.warning("Invalid Mapping File " + _filePath);
            throw e;
        }

        return map;
    }
}
