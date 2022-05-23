package ch.heigvd.statique.commands;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * Commande permettant de nettoyer le site statique
 */
@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

  /** Le chemin des fichier contenant le site à nettoyer **/
  @Parameters(paramLabel = "SITE", description = "The site to clean")
  public Path site;

  /**
   * Appel de la commande
   * @return 0 si tout s'est bien passé
   * @throws IOException
   */
  @Override
  public Integer call() throws IOException {
    Path build = site.resolve("build");
    Utils.deleteRecursive(build);
    return 0;
  }

}