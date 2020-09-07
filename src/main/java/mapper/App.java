package mapper;

import java.io.IOException;

/**
 * Entry Point.
 *
 */
public final class App {

    private App(){

    }

    /**
     * Main Method.
     *
     */
    public static void main(String[] args) {

        MapperService mapperService = new MapperService();
        try {
            System.out.println(mapperService.map("sin", "demo_source_file_path"));
        } catch (IOException e) {

        }
    }
}
