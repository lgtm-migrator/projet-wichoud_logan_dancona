package ch.heig.team;

import picocli.CommandLine;

import picocli.CommandLine.*;

@Command(
        name = "new",
        description = "launch new command",
        mixinStandardHelpOptions = true
)

public class InitCommand implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new InitCommand()).execute(args);
    }

    @Override
    public void run() {
        System.out.println("new command Launched");
    }
}
