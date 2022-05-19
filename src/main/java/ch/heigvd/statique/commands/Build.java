package ch.heigvd.statique.commands;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.Callable;
import org.yaml.snakeyaml.Yaml;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * Commande permettant de construire le site statique
 */
@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

  /** Le fichier de configuration du site statique **/
  private Yaml yaml = new Yaml();

  /** Le chemin des fichiers contenant le site à construire **/
  @Parameters(paramLabel = "SITE", description = "The site to build")
  public Path site;

  /**
   * Appel de la commande
   * @return 0 si tout s'est bien passé
   * @throws IOException
   */
  @Override
  public Integer call() throws IOException {
    // Parsing de la configuration
    Map<String, Object> config = yaml.load(Files.readString(site.resolve("config.yaml")));

    // Initialisation du template
    TemplateLoader loader = new FileTemplateLoader(site.resolve("template").toFile());
    Handlebars handlebars = new Handlebars(loader);
    handlebars.registerHelper("md", new MarkdownHelper());
    Template template = handlebars.compile("layout");

    // Parcours du répertoire du site
    Files.walk(site)
        .filter(file -> file.toString().endsWith(".md"))
        .forEach(source -> {
          try {
            // Séparation des méta-données et du contenu (---)
            String[] array = Files.readString(source).split("---");
            if (array.length != 2) {
              throw new RuntimeException("The page is malformed");
            }
            Map<String, Object> page = yaml.load(array[0]);
            String content = array[1];

            // Injection des variables dans le template (site, page, contenu)
            Context context = Context.newBuilder(new Object())
                .combine("site", config)
                .combine("page", page)
                .combine("content", content)
                .resolver(MapValueResolver.INSTANCE, JavaBeanValueResolver.INSTANCE)
                .build();

            // Rendu de la page web
            String html = template.apply(context);

            // Persistance de la page web
            Path target = site.resolve("build")
                .resolve(site.relativize(source).toString().replace(".md", ".html"));
            Files.createDirectories(target.getParent());
            Files.writeString(target, html, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });

    return 0;
  }

}