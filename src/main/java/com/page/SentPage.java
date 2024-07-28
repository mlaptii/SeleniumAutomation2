package com.page;

import com.page.MailComposing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class SentPage extends MailComposing {
    private static final Logger logger = LogManager.getLogger(SentPage.class);
    //Locators
    By lastMessageInSent = By.xpath("//*[@id='mail-app-component']/div[1]/div/div/div[2]/div/div/div[4]/div/div[1]/ul/li[3]/a/div/div[3]/div[1]/div[1]/span[1]");

    public SentPage(WebDriver driver) {
        super(driver);
    }
    public void checkLastSentEmailSubject(){
        //Check last email Subject
        WebElement lastEmailSubject = driver.findElement((By) lastMessageInSent);
        String lastMessageSubject = lastEmailSubject.getText();
        logger.info("last subject is: " + lastMessageSubject);
        assertEquals("test subject " + dateAndTime, lastMessageSubject);
        logger.info("last entity in sent list corresponds to last sent email");
    }
}
