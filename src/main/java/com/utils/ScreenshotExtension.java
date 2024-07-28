package com.utils;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

public class ScreenshotExtension implements AfterTestExecutionCallback {
    private static boolean testFailed = false;

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isPresent()) {
            testFailed = true;
        }
    }

    public static boolean testFailed() {
        return testFailed;
    }
}
