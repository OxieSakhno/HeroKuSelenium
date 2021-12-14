package com.herokuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestUtilities {

    @Parameters({"description", "username", "password", "confirmationMessage"})
    @Test(priority = 1, groups = {"Smoke", "Positive"})
    public void positiveLoginTest(String description, String username, String password, String confirmationMessage){

        log.info("Starting Positive Login Test");
        log.info("Test description: " + description);

        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();

        LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

        SecureAreaPage secureAreaPage = loginPage.logIn(username, password);
        Assert.assertEquals(secureAreaPage.getPageUrl(), secureAreaPage.getCurrentUrl());
        Assert.assertTrue(secureAreaPage.isLogoutButtonVisible(), "Logout button is not displayed.");

        String actualConfirmationMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(actualConfirmationMessage.contains(confirmationMessage),
                "Actual Success Login Message does not contains Expected Message.");


//        //verifications
//        //NewURL
//        try {
//            Assert.assertEquals(driver.getCurrentUrl(), secureAreaPageUrl);
//            log.info("Actual Url match to expected Url");
//        } catch (Exception e) {
//            log.info("Actual url is NOT match to expected Url");
//            e.printStackTrace();
//        }
//
//        //Logout button
//        try {
//            WebElement logoutButton = driver.findElement(By.xpath("//i[contains(text(), 'Logout')]/.."));
//            Assert.assertTrue(logoutButton.isDisplayed());
//            log.info("Logout button is displayed");
//        } catch (Exception e) {
//            log.info("Logout button is NOT displayed");
//            e.printStackTrace();
//        }
//
//        //success message
//        try {
//            WebElement loginMessage = driver.findElement(By.xpath("//div[@id='flash'][contains(text(), 'You logged into')]"));
//            Assert.assertTrue(loginMessage.getText().contains(confirmationMessage));
//            log.info("'You logged into' message is displayed.");
//        } catch (Exception e) {
//            log.info("'You logged into' message is NOT displayed.");
//            e.printStackTrace();
//        }
    }


    @Parameters({ "description", "username", "password", "confirmationMessage" })
    @Test(priority=2, groups = {"Negative"})
    public void negativeLoginTest(String description, String username, String password, String confirmationMessage){

        log.info("Starting Negative Login Test");
        log.info("Test description: " + description);

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();
        loginPage.logIn(username, password);

        Assert.assertEquals(loginPage.getCurrentUrl(), loginPage.getPageUrl());

        String actualConfirmationMessage = loginPage.getErrorMessageText();
        Assert.assertTrue(actualConfirmationMessage.contains(confirmationMessage));
        log.info("Actual message: ["+ actualConfirmationMessage +"]");
        log.info("Expected message: [" + confirmationMessage +"]");





        /*driver.get(url);

        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        //sleep(1);

        WebElement loginButton  = driver.findElement(By.xpath("//button/i[contains(text(), 'Login')]"));
        loginButton.click();

        WebElement message = driver.findElement(By.xpath("//div[@id='flash']"));

        try {
            Assert.assertTrue(message.getText().contains(confirmationMessage));
            log.info("Error message contains '" + confirmationMessage+ "' text");
        } catch (Exception e) {
            log.info("Error message does NOT contains " + confirmationMessage + " text");
            e.printStackTrace();
        }*/
    }
}
