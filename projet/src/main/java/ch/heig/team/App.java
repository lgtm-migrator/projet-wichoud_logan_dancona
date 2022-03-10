package ch.heig.team;


import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "name",
        description = "",
        mixinStandardHelpOptions = true,
        version = "0.1",
        subcommands = {}
)

public class App implements Runnable {
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("hello");
    }
}
