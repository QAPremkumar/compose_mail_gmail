import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFactory {
    private static Properties properties;

    static {
        properties = loadProperties();
    }

    private static Properties loadProperties() {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getBrowserType() {
        return properties.getProperty("browser.type");
    }

    // Add more methods for other configuration settings as needed
}
