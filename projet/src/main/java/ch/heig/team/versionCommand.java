package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Arrays;

@Command(
        name = "version",
        description = "launch version command",
        mixinStandardHelpOptions = true,
        aliases = { "-v" }
)

public class versionCommand implements Runnable {
    public static void main(String[] args) {
        new CommandLine(new versionCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("12");
        System.exit(1);
    }

}
