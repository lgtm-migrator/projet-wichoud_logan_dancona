package ch.heig.team;

import org.commonmark.Extension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

public class YamlToHTMLParser {
    public static void main(String... args) {
        String markdownValue = "---\n" +
                "key: value\n" +
                "list:\n" +
                "  - value 1\n" +
                "  - value 2\n" +
                "literal: |\n" +
                "  this is literal value.\n" +
                "\n" +
                "  literal values 2\n" +
                "---\n" +
                "\n" +
                "# Java Tutorial";

        String htmlValue = convertMarkdownToHTML(markdownValue);

        System.out.println("Markdown String:");
        System.out.println(markdownValue);
        System.out.println();
        System.out.println("HTML String:");
        System.out.println(htmlValue);
    }

    public static String convertMarkdownToHTML(String markdown) {
        List<Extension> extensions = Arrays.asList(YamlFrontMatterExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().extensions(extensions).build();
        return htmlRenderer.render(document);
    }
}
