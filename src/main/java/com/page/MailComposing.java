package com.page;

import model.PasswordDecodingService;
import org.apache.logging.log4j.Level;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
public class MailComposing {
    private static final Logger logger = LogManager.getLogger(MailComposing.class);

    public WebDriver driver;
    String recepientEmail = "max.laptiy.test@gmail.com";

    //Date/time format
    LocalDateTime currentDateTime = LocalDateTime.now();
    // Format date time
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String formattedDateTime = currentDateTime.format(formatter);

    //String DateTime to use it im this and other classes
    public String dateAndTime = formattedDateTime;

    public String getDateAndTime() {
        return dateAndTime;
    }

    //Locators
    By messageToField = By.id("message-to-field");
    By subjectField = By.id("compose-subject-input");
    By textArea = By.xpath("//*[@aria-label='Message body']");
    By closeMessageButton = By.xpath("//*[@data-test-id='icon-btn-close']");
    By sendEmailButton = By.xpath("//button[@title='Send this email']");

    public MailComposing (WebDriver driver) {
        this.driver=driver;
    }
    public void enterEmailData() {
        driver.findElement((By) messageToField).sendKeys(recepientEmail);
        logger.info("recepient information is added");
        String dateAndTime = formattedDateTime;
        logger.info("date and time which will be used in Subject is: " + dateAndTime);
        driver.findElement((By) subjectField).sendKeys("test subject " + dateAndTime);
        logger.info("subject is added");
        driver.findElement((By) textArea).sendKeys("Test email text");
        logger.info("email text is added");
    }
    public void closeEmailWithoutSending(){
        driver.findElement((By) closeMessageButton).click();
        logger.info("email is closed without sending, should be displayed in Draft section");
    }
    public void sendEmail(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(visibilityOfElementLocated((By) sendEmailButton)).click();
        logger.info("email is sent");
    }
}
