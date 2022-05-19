package ch.heigvd.statique.commands;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * Commande permettant d'initialiser le répertoire du site statique
 */
@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

  /** Le chemin des fichiers contenant le site à initialiser **/
  @Parameters(paramLabel = "SITE", description = "The site to initialize")
  public Path site;

  /**
   * Appel de la commande
   * @return 0 si tout s'est bien passé
   * @throws URISyntaxException en cas d'erreur de syntaxe
   * @throws IOException en cas d'erreur dans les entrées/sorties
   */
  @Override
  public Integer call() throws URISyntaxException, IOException {
    URI uri = this.getClass().getResource("/init").toURI();

    // Initialise un système de fichier zip quand le template est stocké dans un fichier jar
    if (uri.getScheme().equals("jar")) {
      Map<String, String> env = new HashMap<>();
      env.put("create", "true");
      FileSystems.newFileSystem(uri, env);
    }

    Path template = Paths.get(uri);
    Files.walk(template).forEach(source -> {
      try {
        if (Files.isRegularFile(source)) {
          // Le système de fichier (fichier ou jar) est déduit du path.
          // Comme on copie des fichiers depuis le jar au système de fichiers,
          // la méthode toString est appelée pour éviter une mauvaise déduction
          Path target = site.resolve(template.relativize(source).toString());
          Files.createDirectories(target.getParent());
          Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    return 0;
  }

}