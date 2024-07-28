package com.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class Draft {
    private static final Logger logger = LogManager.getLogger(Draft.class);
    WebDriver driver;
    String recepientEmail = "max.laptiy.test@gmail.com";
    //Locators
    By draftMessage = By.xpath("//*[@data-test-id = 'senders-span']");
    By draftMessageSender = By.xpath("//*[@data-test-id = 'senders-span']");
    By draftMessageAmount = By.xpath("//*[@data-test-id = 'displayed-count']");
    By draftIsEmpty = By.xpath("//div[@class='p_a T_0 L_0 W_6D6F I_ZkbNhI H_74JI t_l A_6FsP C_Z281SGl']/span");


    public Draft (WebDriver driver) {
        this.driver=driver;
    }
    public void checkUnfinishedEmailIsDisplayedInDraft() {
        WebElement draftName = driver.findElement((By) draftMessageSender);
        WebElement draftNumber = driver.findElement((By) draftMessageAmount);
        // Extract text content from the element
        String draftNameT = draftName.getText();
        String draftNumberT = draftNumber.getText();
        // Initialize variable with the extracted data
        logger.info("email recipient is defined: " + draftNameT);
        logger.info("draft emails amount is: " + draftNumberT);

        assertEquals(recepientEmail, draftNameT);
        assertEquals("1", draftNumberT);
    }
    public void selectDraftMessageFromTheList(){
        driver.findElement((By) draftMessage).click();
    }
    public void checkDraftListIsEmpty(){
        WebElement emptyDraft = driver.findElement((By) draftIsEmpty);
        String draftEmpty = emptyDraft.getText();
        logger.info(draftEmpty + " notification is displayed");
        assertEquals("Drafts is empty", draftEmpty);
    }
}
