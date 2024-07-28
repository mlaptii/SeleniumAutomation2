package com.tests;

import com.configs.TestDataProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import com.page.LoginPage;
import com.page.HomePage;
import com.page.MailComposing;
import com.page.Draft;
import com.page.SentPage;
import com.page.Logout;

import java.util.concurrent.TimeUnit;

public class Tests extends CommonConditions {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);
    MailComposing mailComposing = new MailComposing(driver);
    Draft draft = new Draft(driver);
    SentPage sentPage = new SentPage(driver);
    Logout logOut = new Logout(driver);

    public String email;
    public String password;

    public void loginCredentials (String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Test
    public void emailSending() {
        String email = TestDataProvider.getUserData().get(0);
        String password = TestDataProvider.getUserData().get(1);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        homePage.clickComposeEmailButton();
        mailComposing.enterEmailData();
        mailComposing.closeEmailWithoutSending();
        homePage.selectDraftButton();
        draft.checkUnfinishedEmailIsDisplayedInDraft();
        draft.selectDraftMessageFromTheList();
        mailComposing.sendEmail();
        homePage.selectDraftButton();
        draft.checkDraftListIsEmpty();
        homePage.selectSentButton();
        sentPage.checkLastSentEmailSubject();
        logOut.logOut();
    }
}
