package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "serve",
        description = "launch serve command",
        mixinStandardHelpOptions = true,
        version = "0.1"
)

public class serveCommand implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new serveCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("serve command Launched");
    }
}
