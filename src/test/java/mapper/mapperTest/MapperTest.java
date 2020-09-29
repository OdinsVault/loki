package mapper.mapperTest;

import mapper.mapper.Mapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import picocli.CommandLine;

public class MapperTest {

    @Test
    public void simpleMapTest(){
        Mapper mapper = new Mapper();
        CommandLine commandLine = new CommandLine(mapper);

        int exitCode = commandLine.execute("-l", "sn", "-src", "SimplyCodes/code1.simply");

        Assert.assertEquals(exitCode, -0);
    }

}
