package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "serve",
        description = "launch serve command",
        mixinStandardHelpOptions = true,
        aliases = {"-s"}
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
