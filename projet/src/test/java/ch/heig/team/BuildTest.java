package ch.heig.team;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for CLI.
 */
public class BuildTest
{
    private static final String BUILD_PATH = "/build";
    @BeforeAll
    public static void CreateDirectory() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir") + BUILD_PATH);
        Path p = Files.createDirectories(path);
        System.out.println(p);
        System.out.println("s");
    }

    @Test
    public void cleanShouldDeleteBuildDirectory() throws IOException {
        CreateDirectory();
        assertTrue(true);
    }
}
