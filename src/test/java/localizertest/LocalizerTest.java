package localizertest;

import mapper.localizer.Localizer;
import org.testng.Assert;
import org.testng.annotations.Test;
import picocli.CommandLine;

import java.io.File;

public class LocalizerTest {

    @Test
    public void localizerTest(){
        Localizer localizer = new Localizer();
        CommandLine commandLine = new CommandLine(localizer);

        File file = new File("src/test/TestCodes/sampleOrigin_original.simply");

        if(file.exists() && !file.isDirectory()){
            file.delete();
        }

        commandLine.execute("-t", "sn", "-src", "src/test/TestCodes/sampleOrigin.simply");

        boolean isExist = file.exists();

        Assert.assertTrue(isExist, "Localizing error");

    }
}
