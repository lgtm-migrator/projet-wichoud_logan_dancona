package ch.heig.team;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void shouldConvertTitles()
    {
        String markdownValue = "# heading h1\n"
                + "## heading h2\n"
                + "### heading h3\n"
                + "#### heading h4\n"
                + "---";

        String htmlValue = MarkdownToHTMLParser.convert_string_markdown_to_HTML(markdownValue);

    assertEquals(
        htmlValue,
        "<h1>heading h1</h1>\n"
            + "<h2>heading h2</h2>\n"
            + "<h3>heading h3</h3>\n"
            + "<h4>heading h4</h4>\n"
            + "<hr />\n");

        System.out.println("Markdown String:");
        System.out.println(markdownValue);
        System.out.println("HTML String:");
        System.out.println(htmlValue);
    }

    @Test
    public void shouldConvertParagraphs()
    {
        String markdownValue = "Le début de mon article\n"
                + "\n"
                + "La suite de mon article\n"
                + "\n"
                + "La fin de mon article\n";

        String htmlValue = MarkdownToHTMLParser.convert_string_markdown_to_HTML(markdownValue);

    assertEquals(
        htmlValue,
        "<p>Le début de mon article</p>\n"
            + "<p>La suite de mon article</p>\n"
            + "<p>La fin de mon article</p>\n");

        System.out.println("Markdown String:");
        System.out.println(markdownValue);
        System.out.println("HTML String:");
        System.out.println(htmlValue);
    }

    @Test
    public void shouldConvertImage()
    {
        String markdownValue = "![Une image](./image.png)";

        String htmlValue = MarkdownToHTMLParser.convert_string_markdown_to_HTML(markdownValue);

    assertEquals(htmlValue, "<p><img src=\"./image.png\" alt=\"Une image\" /></p>\n");

        System.out.println("Markdown String:");
        System.out.println(markdownValue);
        System.out.println("HTML String:");
        System.out.println(htmlValue);
    }

    @Test
    public void shouldConvertBulletList()
    {
        String markdownValue = "- Un élément\n"
                + "- Un autre élément\n"
                + "- Encore un autre élément\n";

        String htmlValue = MarkdownToHTMLParser.convert_string_markdown_to_HTML(markdownValue);

    assertEquals(
        htmlValue,
        "<ul>\n"
            + "<li>Un élément</li>\n"
            + "<li>Un autre élément</li>\n"
            + "<li>Encore un autre élément</li>\n"
            + "</ul>\n");

        System.out.println("Markdown String:");
        System.out.println(markdownValue);
        System.out.println("HTML String:");
        System.out.println(htmlValue);
    }

    @Test
    public void shouldConvertEnumeration()
    {
        String markdownValue = "1. Premier élément\n"
                + "2. Deuxième élément\n"
                + "3. Troisième élément\n";

        String htmlValue = MarkdownToHTMLParser.convert_string_markdown_to_HTML(markdownValue);

    assertEquals(
        htmlValue,
        "<ol>\n"
            + "<li>Premier élément</li>\n"
            + "<li>Deuxième élément</li>\n"
            + "<li>Troisième élément</li>\n"
            + "</ol>\n");

        System.out.println("Markdown String:");
        System.out.println(markdownValue);
        System.out.println("HTML String:");
        System.out.println(htmlValue);
    }

    @Test
    public void shouldReadFromInputStream() throws IOException {
        assertEquals(
                "# Mon premier article\n"
                        + "## Mon sous-titre\n"
                        + "Le contenu de mon article.\n"
                        + "![Une image](./image.png)\n",
                MarkdownToHTMLParser.convert_file_markdown_to_HTML(
                        AppTest.class.getResourceAsStream("/test/fichier_donnees.md")));
    }
}
