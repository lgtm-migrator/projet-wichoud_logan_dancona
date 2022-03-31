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
                serveCommand.class,
                versionCommand.class,
                HelpCommand.class
        }
)

public class Main implements Runnable {
    @CommandLine.Option(names = {"-v", "--version"}, description = "print the version of static generator")
    boolean versionRequested;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        if (exitCode != 0) System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Static website generator");
        //CommandLine.usage(this, System.out);
    }
}
