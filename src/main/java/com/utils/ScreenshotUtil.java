package com.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String filename) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void captureScreenshotWithTimestamp(WebDriver driver, String directory, String prefix) {
        String filename = directory + "/" + prefix + "_" + System.currentTimeMillis() + ".png";
        captureScreenshot(driver, filename);
    }
}
