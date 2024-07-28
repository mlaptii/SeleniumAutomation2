package com.page;

import model.PasswordDecodingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
public class LoginPage {
    private static PasswordDecodingService service = new PasswordDecodingService();
    private static final Logger logger = LogManager.getLogger(LoginPage.class);


    WebDriver driver;
    /*String email = "mlaptii@yahoo.com";
    String password = "3303031325a21353035306";*/
    //Locators
    By goBottonButton = By.id("scroll-down-btn");
    By acceptCookiesButton = By.xpath("//*[@name='agree']");
    By mailButton = By.id("ybarMailLink");
    By usernameInput = By.id("login-username");
    By nextButton = By.id("login-signin");
    By passwordInput = By.id("login-passwd");

    public LoginPage (WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 10 seconds timeout
        wait.until(ExpectedConditions.elementToBeClickable(goBottonButton)).click();
        wait.until(visibilityOfElementLocated((By) acceptCookiesButton)).click();
        logger.info("cookie policy accepted");

        wait.until(visibilityOfElementLocated((By) mailButton)).click();
        logger.info("mail icon is selected - login started");
        driver.findElement((By) usernameInput).sendKeys(email);
        driver.findElement((By) nextButton).click();
        logger.info("email entered");
        wait.until(visibilityOfElementLocated((By) passwordInput)).sendKeys(service.decodePassword(password));
        driver.findElement((By) nextButton).click();
       // driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        logger.info("password is entered");
    }
}

/*import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

   + @FindBy(id = "username")
    private WebElement usernameField;

   + @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    +public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}*/
