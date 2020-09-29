package mapper.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    /**
     * Get keyword mapping file from mappingRegistry
     * @param lngId - language id
     * @return Map data structure of mapping YAML file
     * @throws IOException
     */
    public static Map<String, String> getMappingById(String lngId) throws IOException {
        // Get mapping registry
        Map<String, String> mappingRegistry = YamlUtils.yamlToMap("mappings/MappingRegistry.yml");

        // Get mapping file from registry
        String mappingFilePath = mappingRegistry.get(lngId);

        // Get mapping file
        Map<String, String> mapping = YamlUtils.yamlToMap(mappingFilePath);

        return mapping;
    }

    public static Map<String, String> getMappingById(String lngId, boolean isInverse) throws IOException {
        Map<String, String>  map = getMappingById(lngId);

        Map<String, String> inverted = new HashMap<>();
        for(String key : map.keySet()){
            inverted.put(map.get(key), key);
        }

        return inverted;
    }

}
