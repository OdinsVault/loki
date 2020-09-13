package mapper;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "mapper",
    description = "Main command",
    subcommands = {
        MapperService.class
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
