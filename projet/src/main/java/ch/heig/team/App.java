package ch.heig.team;


import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(
        name = "name",
        description = "",
        mixinStandardHelpOptions = true,
        version = "0.1",
        subcommands = {
                newCommand.class,
                cleanCommand.class,
                buildCommand.class,
                serveCommand.class
        }
)

public class App implements Runnable {

    @Parameters(index = "0", description = "the mode you want to choose. ")
    @Option(names = {"-n", "--new"}, description = "new")
    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
        System.out.println("hello");
    }
}
