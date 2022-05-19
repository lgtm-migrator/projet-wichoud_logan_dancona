package ch.heigvd.statique.commands;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Classe qui s'occupe du parsing de markdown et le transforme en html
 */
public class MarkdownHelper implements Helper<String> {

  /** Le parseur des fichiers de configuration */
  private Parser parser = Parser.builder().build();

  /** Le moteur de rendu html */
  private HtmlRenderer renderer = HtmlRenderer.builder().build();

  /**
   * Applique le parsing sur une chaîne de caractères en markdown et rend le fichier html ainsi générée
   * @param markdown la chaîne de caractères à parser
   * @param options les éventuelles options de parsing
   * @return le fichier html généré par la chaîne de caractères
   */
  @Override
  public Object apply(String markdown, Options options) {
    Node document = parser.parse(markdown);
    return renderer.render(document);
  }

}
