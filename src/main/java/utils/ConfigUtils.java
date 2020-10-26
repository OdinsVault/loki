package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

// Load configurations files from here
public class ConfigUtils {

    public static ConfigDescriptor loadConfigs(String path) throws FileNotFoundException {

        // read configurations file and create ConfigDescriptor object
        Yaml yaml = new Yaml();

        File configFile = new File(path);
        InputStream inputStream = new FileInputStream(configFile);

        // Create config descriptor with validation
        ConfigDescriptor configDescriptor = yaml.load(inputStream);
        return configDescriptor;
    }
}
