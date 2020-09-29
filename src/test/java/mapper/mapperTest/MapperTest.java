package mapper.mapperTest;

import mapper.mapper.Mapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import picocli.CommandLine;

import java.io.File;

public class MapperTest {

    @Test
    public void simpleMapTest(){
        Mapper mapper = new Mapper();
        CommandLine commandLine = new CommandLine(mapper);

        File file = new File("src/test/TestCodes/sample_target.simply");

        if(file.exists() && !file.isDirectory()){
            file.delete();
        }

        int exitCode = commandLine.execute("-l", "sn", "-src", "src/test/TestCodes/sample.simply");

        boolean exist = file.exists() && !file.isDirectory();

        Assert.assertEquals(exist, true, "Mapping Error");
    }

}
