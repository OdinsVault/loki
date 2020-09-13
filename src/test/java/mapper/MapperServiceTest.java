package mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.*;

class MapperServiceTest {

    MapperService mapperService = new MapperService();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void main() {
        String[] args = new String[]{
            "-l", "sn", "-src", "SimplyCodes/code1.simply", "-m", "mappings/MappingRegistry.yml"
        };
        int exitcode = new CommandLine(new MapperService()).execute(args);
        assertEquals(0, exitcode);
    }

    @Test
    void run() {
    }
}
