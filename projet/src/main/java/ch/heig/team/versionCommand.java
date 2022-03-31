package ch.heig.team;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.net.URL;
import java.util.Properties;

@Command(
        name = "version",
        description = "launch version command",
        mixinStandardHelpOptions = true,
        version = "0.1"
)

public class versionCommand implements Runnable {
    //@CommandLine.Option(names = { "-V", "--version" }, versionHelp = true, description = "print version information and exit")
    //boolean versionRequested;
    public static void main(String[] args) {
        new CommandLine(new versionCommand()).execute(args);
    }

    @Override
    public void run() {
        if (true) {
            System.out.println("The version is: ");
            System.exit(1);
        }
    }

    static class PropertiesVersionProvider implements CommandLine.IVersionProvider {
        public String[] getVersion() throws Exception {
            URL url = getClass().getResource("/version.txt");
            if (url == null) {
                return new String[] {"No version.txt file found in the classpath. Is examples.jar in the classpath?"};
            }
            Properties properties = new Properties();
            properties.load(url.openStream());
            return new String[] {
                    properties.getProperty("Application-name") + " version \"" + properties.getProperty("Version") + "\"",
                    "Built: " + properties.getProperty("Buildtime"),
            };
        }
    }
}
