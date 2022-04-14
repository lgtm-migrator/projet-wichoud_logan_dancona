package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command(
        name = "clean",
        description = "launch clean command",
        mixinStandardHelpOptions = true,
        aliases = {"-c"}
)

public class CleanCommand implements Runnable {

   @CommandLine.Parameters(paramLabel = "PATH", description = "The site to clean")
   public Path path;

   public static void main(String[] args) {
      new CommandLine(new CleanCommand()).execute(args);
   }

   @Override
   public void run() {
      Path path = Paths.get(System.getProperty("user.dir") + "//build");
      File file = path.toFile();
      deleteDirectory(file);
   }

   private void delete(File file) throws IOException {
      if (file.isDirectory()) {
         File[] entries = file.listFiles();
         if (entries != null) {
            for (File entry : entries) {
               delete(entry);
            }
         }
      }
      if (!file.delete()) {
         throw new IOException("Failed to delete " + file);
      }
   }

   boolean deleteDirectory(File directoryToBeDeleted) {
      File[] allContents = directoryToBeDeleted.listFiles();
      if (allContents != null) {
         for (File file : allContents) {
            deleteDirectory(file);
         }
      }
      return directoryToBeDeleted.delete();
   }
}
