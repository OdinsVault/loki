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

        File file = new File("src/test/TestCodes/sampleNative_target.simply");

        if(file.exists() && !file.isDirectory()){
            file.delete();
        }

        commandLine.execute("-l", "sn", "-src", "src/test/TestCodes/sampleNative.simply");

        boolean exist = file.exists() && !file.isDirectory();

        Assert.assertTrue(exist, "Mapping Error");
    }

}
