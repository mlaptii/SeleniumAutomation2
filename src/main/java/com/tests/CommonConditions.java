package com.tests;

import com.configs.YAMLConfig;
import com.configs.TestDataProvider;
//import configs.YAMLConfig;
//import driver.DriverSingleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import utils.ScreenshotTestWatcher;
import com.utils.ScreenshotExtension;
import com.utils.ScreenshotUtil;
import org.junit.rules.TestName;
//import utils.TestListener;

import java.util.Collection;

@ExtendWith(ScreenshotExtension.class)

public class CommonConditions {

    public static WebDriver driver;

    @Rule
    public TestName testName = new TestName();

    @BeforeAll
    public static void setUp() {
        String browser = YAMLConfig.getEnvConfig("browser");
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
        driver.get(YAMLConfig.getEnvConfig("baseUrl"));
        // Initialize the ScreenshotTestWatcher with the driver
    }

    @AfterEach
    public void screenshotIfFailed(){
        if (ScreenshotExtension.testFailed()) {
            // Capture screenshot with dynamic filename only if the test failed
            ScreenshotUtil.captureScreenshotWithTimestamp(driver, "screenshots", "test");
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            // Take a screenshot if the test failed
            driver.quit();
        }
        /*public static WebDriver getDriverInstance () {
            return driver;
        }*/
    }
}
