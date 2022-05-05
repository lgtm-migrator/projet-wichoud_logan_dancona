package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "build",
        description = "launch build command",
        mixinStandardHelpOptions = true,
        aliases = {"-b"}
)

public class BuildCommand implements Runnable {

   public static void main(String[] args) {
      new CommandLine(new BuildCommand()).execute(args);
   }

   @Override
   public void run() {
      System.out.println("build command Launched");
   }
}