package apptest;

import mapper.App;
import org.testng.Assert;
import org.testng.annotations.Test;
import picocli.CommandLine;

import java.io.File;

public class AppTest {

    @Test
    public void appTest(){
        App app = new App();
        CommandLine commandLine = new CommandLine(app);

        commandLine.execute();
    }

}
