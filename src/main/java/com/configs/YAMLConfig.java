package com.configs;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YAMLConfig {
    private static Map<String, Object> config;
    private static Map<String, Object> users;
    private static Map<String, Object> currentEnvConfig;
    private static Map<String, String> currentUserConfig;

    static {
        Yaml yaml = new Yaml();
        try (InputStream configStream = YAMLConfig.class.getClassLoader().getResourceAsStream("config.yaml");
             InputStream userStream = YAMLConfig.class.getClassLoader().getResourceAsStream("users.yaml")) {

            if (configStream == null) {
                throw new RuntimeException("Main config file not found: config.yaml");
            }
            if (userStream == null) {
                throw new RuntimeException("User file not found: users.yaml");
            }

            config = yaml.load(configStream);
            users = yaml.load(userStream);

            String env = (String) config.get("env");
            try (InputStream envStream = YAMLConfig.class.getClassLoader().getResourceAsStream("env_" + env + ".yaml")) {
                if (envStream == null) {
                    throw new RuntimeException("Environment file not found: env_" + env + ".yaml");
                }
                currentEnvConfig = yaml.load(envStream);
            }

            String userRole = (String) currentEnvConfig.get("userRole");
            currentUserConfig = (Map<String, String>) users.get(userRole);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration files", e);
        }
    }

    public static String getEnvConfig(String key) {
        return (String) currentEnvConfig.get(key);
    }

    public static String getUserConfig(String key) {
        return currentUserConfig.get(key);
    }
}

