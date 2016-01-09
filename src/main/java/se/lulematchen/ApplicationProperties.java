package se.lulematchen;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
    private static ApplicationProperties instance = null;

    private Properties properties;

    private ApplicationProperties() {
        properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void loadProperties() {
        properties = new Properties();

        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            properties.load(contextClassLoader.getResourceAsStream("env.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ApplicationProperties getInstance() {
        if (instance == null) {
            instance = new ApplicationProperties();
        }

        return instance;
    }
}
