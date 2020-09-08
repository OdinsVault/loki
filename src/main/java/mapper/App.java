package mapper;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Entry Point.
 *
 */
public final class App {

    private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private App(){

    }

    /**
     * Main Method.
     *
     */
    public static void main(String[] args) {
        try{
            Run(args);
        }catch (Exception e){
            LOGGER.warning("Something wrong");
        }
    }

    public static void Run(String[] args) throws IOException {

        MapperService mapperService = new MapperService();

        mapperService.map("sn", "../mapper/SimplyCodes/code1.simply");
    }
}
