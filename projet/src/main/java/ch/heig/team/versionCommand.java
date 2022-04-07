package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Arrays;
import java.util.Properties;

@Command(
        name = "version",
        description = "launch version command",
        mixinStandardHelpOptions = true,
        aliases = {"-v"}
)

public class versionCommand implements Runnable {
   public static void main(String[] args) {
      new CommandLine(new versionCommand()).execute(args);
   }

   @Override
   public void run() {
      try {
         //create a new property file
         final Properties properties = new Properties();
         //load the one we store the version in
         properties.load(versionCommand.class.getClassLoader().getResourceAsStream("project.properties"));
         //get the version
         System.out.println("Statique :\t" + properties.getProperty("version"));
      } catch (Exception e) {
         System.out.println("Error while reading version number:\n" + e.getMessage());
      }
      System.exit(1);
   }

}
