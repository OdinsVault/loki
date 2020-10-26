package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

// Load configurations files from here
public class ConfigUtils {

    // Configuration file path
    private String path;

    public ConfigUtils(String path) {
        this.path = path;
    }

    public ConfigDescriptor loadConfigs(){

        // read configurations file and create ConfigDescriptor object
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(this.path);
        ConfigDescriptor configDescriptor = yaml.load(inputStream);
        return configDescriptor;
    }
}
