package com.configs;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestDataProvider {

    public static List<String> getUserData() {
        return Arrays.asList(
                com.configs.YAMLConfig.getUserConfig("email"),
                com.configs.YAMLConfig.getUserConfig("password")
        );
    }
}
