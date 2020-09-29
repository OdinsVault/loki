package mapper.localizer;

import picocli.CommandLine;

@CommandLine.Command(name = "localize", description = "Localize Simply code to native language")
public class Localizer implements Runnable{

    @Override
    public void run() {
        System.out.println("Localize");
    }

}
