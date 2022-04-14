package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "serve",
        description = "launch serve command",
        mixinStandardHelpOptions = true,
        aliases = {"-s"}
)

public class ServeCommand implements Runnable {

   public static void main(String[] args) {
      new CommandLine(new ServeCommand()).execute(args);
   }

   @Override
   public void run() {
      System.out.println("serve command Launched");
   }
}
