package ch.heig.team;

import picocli.CommandLine;

import picocli.CommandLine.*;

@Command(
        name = "new",
        description = "launch new command",
        mixinStandardHelpOptions = true,
        version = "0.1"
)

public class newCommand implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new newCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("new command Launched");
    }
}
