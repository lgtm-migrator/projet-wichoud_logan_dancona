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

  /**
   * Le fichier de configuration du site statique
   **/
  private Yaml yaml = new Yaml();

  /**
   * Le chemin des fichiers contenant le site à construire
   **/
  @Parameters(paramLabel = "SITE", description = "The site to build")
  public Path site;

  /**
   * Appel de la commande
   *
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

                buildHtmlFromMarkdown(source, config, template);

              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            });

    return 0;
  }

  /**
   * Sépare les données se trouvant à un chemin en un tableau contenant deux éléments : les métadonnées et le contenu
   *
   * @param source le chemin (Path) de la source de laquelle on veut séparer les données
   * @return un tableau contenant les métadonnées (élément 0) et le contenu (élément 1)
   * @throws IOException
   */
  private String[] metadataAndContentSeparation(Path source) throws IOException {

    String[] metadonneesEtContenu = Files.readString(source).split("---");

    if (metadonneesEtContenu.length != 2) {
      throw new RuntimeException("The page is malformed");
    }
    return metadonneesEtContenu;
  }

  /**
   * Récupère les métadonnées uniquement, à partir d'un tableau contenant des métadonnées et du contenu
   *
   * @param metaDataAndContent tableau contenant les métadonnées et le contenu
   * @return les métadonnées
   */
  private Map<String, Object> getMetaDataFrom(String[] metaDataAndContent) {
    return yaml.load(metaDataAndContent[0]);
  }

  /**
   * Récupère le contenu uniquement, à partir d'un tableau contenant des métadonnées et du contenu
   *
   * @param metaDataAndContent tableau contenant les métadonnées et le contenu
   * @return le contenu
   */
  private String getContentFrom(String[] metaDataAndContent) {
    return metaDataAndContent[1];
  }

  /**
   * Création d'un contexte à partir d'une configuration, de métadonnées et de contenu
   */
  private Context injectVariablesInTemplate(Map<String, Object> config, Map<String, Object> metaData, String content) {
    return Context.newBuilder(new Object())
            .combine("site", config)
            .combine("page", metaData)
            .combine("content", content)
            .resolver(MapValueResolver.INSTANCE, JavaBeanValueResolver.INSTANCE)
            .build();
  }

  /**
   * Injection d'un contexte dans un template et rendu du html - traduction du format md en html
   * @param template le template à injecter
   * @param context la configuration, les métadonnées et le contenu à injecter
   * @return le contenu html équivalent
   * @throws IOException
   */
  private String applyTemplate(Template template, Context context) throws IOException {
    return template.apply(context);
  }

  /**
   * Ajout du code html créé dans un fichier .html dans un sous-dossier /build
   * @param source le chemin source duquel on part
   * @return le chemin ainsi créé, étant le chemin source avec ajouté "/buid" et y remplaçant l'extension ".md" par ".html"
   */
  private Path createTarget (Path source){
    return site.resolve("build")
            .resolve(site.relativize(source).toString().replace(".md", ".html"));
  }

  /**
   * Crée le chemin target, les directories parents et écrit le contenu html créé dans la target
   * @param source chemin source duquel on crée la target
   * @param html code html que l'on veut mettre au chemin target
   * @throws IOException
   */
  private void consolidateHtml(Path source, String html) throws IOException {
    Path target = createTarget(source);
    Files.createDirectories(target.getParent());
    Files.writeString(target, html, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
  }

  /**
   * Converti un fichier markdown en fichier html, en le mettant dans un sous dossier /build à partir du chemin du fichier source
   * @param source le chemin du fichier source
   * @param config le fichier de configuration, écrit en yaml
   * @param template le template html que l'on va utiliser
   * @throws IOException
   */
  private void buildHtmlFromMarkdown(Path source, Map<String, Object> config, Template template) throws IOException {
    // Séparation des méta-données et du contenu (---)
    String[] metaDataAndContent = metadataAndContentSeparation(source);

    Map<String, Object> metaData = getMetaDataFrom(metaDataAndContent);
    String content = getContentFrom(metaDataAndContent);

    // Création du contexte (site, page, contenu)
    Context context = injectVariablesInTemplate(config, metaData, content);

    // Injection du contexte dans un template ; rendu html
    String html = applyTemplate(template, context);

    // Consolidation et persistance de la page web
    consolidateHtml(source, html);
  }

}