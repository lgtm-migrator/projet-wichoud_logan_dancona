package ch.heigvd.statique.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * Classe contenant des fonctions utilitaires additionnelles
 */
public class Utils {

  /**
   * Permet de supprimer récursivement le site statique
   * @param directory le chemin du répertoire contenant le site à supprimer
   * @throws IOException
   */
  public static void deleteRecursive(Path directory) throws IOException {
    if (Files.exists(directory)) {
      Files.walk(directory)
          .sorted(Comparator.reverseOrder())
          .map(Path::toFile)
          .forEach(File::delete);
    }
  }

}
