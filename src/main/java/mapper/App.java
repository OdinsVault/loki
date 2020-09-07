package mapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class App {

    private App(){

    }

    // TODO: Init logger
    //private static final Logger logger = LogManager.getLogger(String.valueOf(App.class));

    public static void main(String[] args) {

        MapperService mapperService = new MapperService();
        try {
            System.out.println(mapperService.map("sin", "demo_source_file_path"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
