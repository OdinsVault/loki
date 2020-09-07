package mapper;

import java.io.IOException;

public final class App {
    private App() {
    }

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
