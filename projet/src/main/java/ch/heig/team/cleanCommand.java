package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "clean",
        description = "launch clean command",
        mixinStandardHelpOptions = true
)

public class cleanCommand implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new cleanCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("clean command Launched");
    }
}
