package mapper.utils;

// Load configurations files from here
public class ConfigUtils {

    // Configuration file path
    private String path;

    public ConfigUtils(String path) {
        this.path = path;
    }

    public ConfigDescriptor loadConfigs(){

        // read configurations file and create ConfigDescriptor object
        return new ConfigDescriptor();
    }
}
