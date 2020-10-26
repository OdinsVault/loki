import com.sun.istack.internal.localization.Localizer;
import mapper.Mapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "mapper",
    description = "Main command",
    subcommands = {
        Mapper.class,
        Localizer.class
    }
)
public class App implements Runnable{

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("Print Description");
    }
}
