package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {
    private WebDriver driver;
    String url = "http://the-internet.herokuapp.com/login";
    String secureAreaPageUrl = "http://the-internet.herokuapp.com/secure";

    @BeforeMethod(alwaysRun = true)
    private void setUp(){
        //create driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        //close the driver
        driver.quit();
    }


    @Parameters({"description", "username", "password", "confirmationMessage"})
    @Test(priority = 1, groups = {"Smoke"})
    public void positiveLoginTest(String description, String username, String password, String confirmationMessage){
        System.out.println("\nPositive Login Test is running.");
        System.out.println("Description: " + description);

        //open test page
        driver.get(url);
        sleep(2);

        //enter username
        WebElement userNameField = driver.findElement(By.xpath("//*[@id='username']"));
        userNameField.sendKeys(username);

        //enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        sleep(1);

        //click login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        sleep(1);

        //verifications
        //NewURL
        try {
            Assert.assertEquals(driver.getCurrentUrl(), secureAreaPageUrl);
            System.out.println("Actual Url match to expected Url.");
        } catch (Exception e) {
            System.out.println("Actual url is NOT match to expected url.");
            e.printStackTrace();
        }

        //Logout button
        try {
            WebElement logoutButton = driver.findElement(By.xpath("//i[contains(text(), 'Logout')]/.."));
            Assert.assertTrue(logoutButton.isDisplayed());
            System.out.println("Logout button is displayed.");
        } catch (Exception e) {
            System.out.println("Logout button is NOT displayed.");
            e.printStackTrace();
        }

        //success message
        try {
            WebElement loginMessage = driver.findElement(By.xpath("//div[@id='flash'][contains(text(), 'You logged into')]"));
            Assert.assertTrue(loginMessage.getText().contains(confirmationMessage));
            System.out.println("'You logged into' message is displayed.\n");
        } catch (Exception e) {
            System.out.println("'You logged into' message is NOT displayed.\n");
            e.printStackTrace();
        }
    }


    @Parameters({ "description", "username", "password", "confirmationMessage" })
    @Test(priority=2, groups = {"Negatives"})
    public void negativeLoginTest(String description, String username, String password, String confirmationMessage){
        System.out.println("\nStarting Negative Login Test.");
        System.out.println("Description: " + description);

        driver.get(url);

        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        sleep(2);

        WebElement loginButton  = driver.findElement(By.xpath("//button/i[contains(text(), 'Login')]"));
        loginButton.click();

        WebElement message = driver.findElement(By.xpath("//div[@id='flash']"));

        try {
            Assert.assertTrue(message.getText().contains(confirmationMessage));
            System.out.println("Error message contains '" + confirmationMessage+ "' text.\n");
        } catch (Exception e) {
            System.out.println("Error message does NOT contains " + confirmationMessage + " text.\n");
            e.printStackTrace();
        }
    }

    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
