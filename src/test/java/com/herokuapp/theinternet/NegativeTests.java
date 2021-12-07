package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {
    String url = "https://the-internet.herokuapp.com/login";

    @Parameters({ "wrongUsername", "password", "errorMessageUserName" })
    @Test(priority=1, groups = {"Negatives"})
    public void incorrectUserName(String wrongUsername, String password, String errorMessageUserName){
        System.out.println("\nStarting Incorrect UserName Test.");

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);

        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys(wrongUsername);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

         sleep(2);

        WebElement loginButton  = driver.findElement(By.xpath("//button/i[contains(text(), 'Login')]"));
        loginButton.click();

        WebElement message = driver.findElement(By.xpath("//div[@id='flash']"));

        try {
            Assert.assertTrue(message.getText().contains(errorMessageUserName));
            System.out.println("Error message contains 'Your username is invalid' words.\n");
        } catch (Exception e) {
            System.out.println("Error message does NOT contains " + errorMessageUserName + " words.");
            e.printStackTrace();
        }

        driver.quit();
    }

    @Parameters({"userName", "wrongPassword", "errorMessageWrongPassword"})
    @Test(priority=2, groups = { "Smoke", "Negatives" })
    public void incorrectPassword(String userName, String WrongPassword, String errorMessageWrongPassword){

        System.out.println("Starting Incorrect Password Test");

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);

        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys(userName);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(WrongPassword);

        sleep(2);

        WebElement loginButton  = driver.findElement(By.xpath("//button/i[contains(text(), 'Login')]"));
        loginButton.click();

        WebElement message = driver.findElement(By.xpath("//div[@id='flash']"));

        try {
            Assert.assertTrue(message.getText().contains(errorMessageWrongPassword));
            System.out.println("Error message contains 'Your password is invalid' words.");
        } catch (Exception e) {
            System.out.println("Error message does NOT contains " + errorMessageWrongPassword + " words.");
            e.printStackTrace();
        }

        driver.quit();
    }


    private void  sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
