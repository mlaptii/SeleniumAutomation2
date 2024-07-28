package com.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
public class Logout {
    private static final Logger logger = LogManager.getLogger(Logout.class);

    WebDriver driver;
    //Locators
    By accountIcon = By.xpath("//*[@for='ybarAccountMenu']");
    By signOutButton = By.xpath("//*[@data-soa='Sign out of all']");
    public Logout (WebDriver driver) {
        this.driver=driver;
    }
    public void logOut(){
        //Sign out option
        WebElement ele = driver.findElement((By) accountIcon);
        //Creating object of an Actions class
        Actions action = new Actions(driver);
        //Performing the mouse hover action on the target element.
        action.moveToElement(ele).perform();
        driver.findElement((By) signOutButton).click();
        logger.info("user is logged out");
    }
}
