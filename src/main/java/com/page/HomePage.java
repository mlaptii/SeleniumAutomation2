package com.page;

import model.PasswordDecodingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
public class HomePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    WebDriver driver;
    //Locators
    By composeButton = By.xpath("//*[@data-test-id='compose-button']");
    By draftMessagesButton = By.xpath("//*[@data-test-folder-name ='Draft']");
    By sentMessagesButton = By.xpath("//*[@data-test-folder-name ='Sent']");

    public HomePage (WebDriver driver) {
        this.driver=driver;
    }
    public void clickComposeEmailButton() {
        logger.info("Homepage is opened");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(visibilityOfElementLocated((By) composeButton)).click();
        logger.info("empty email form is opened");
    }
    public void selectDraftButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(visibilityOfElementLocated((By) draftMessagesButton)).click();
        logger.info("'draft' page is opened");
    }
    public void selectSentButton(){
        driver.findElement((By) sentMessagesButton).click();
        logger.info("'sent' page is opened");
    }
}