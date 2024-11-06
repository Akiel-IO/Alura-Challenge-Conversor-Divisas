package com.alura.conversor.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    private static Properties properties;
    private static final String configFile = "src/main/resources/config.properties";

    static {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de configuración: "+ e.getMessage());
        }
    }

    public static String getApiKey() {
        return properties.getProperty("api.key");
    }

}
