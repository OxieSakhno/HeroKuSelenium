package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
    String url = "https://the-internet.herokuapp.com/login";

    @Test
    public void incorrectUserName(){
        System.out.println("Starting Incorrect UserName Test.");

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);

        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys("wrongUserName");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("SuperSecretPassword!");

         sleep(2);

        WebElement loginButton  = driver.findElement(By.xpath("//button/i[contains(text(), 'Login')]"));
        loginButton.click();

        String errorMessage = "Your username is invalid";
        WebElement message = driver.findElement(By.xpath("//div[@id='flash']"));

        try {
            Assert.assertTrue(message.getText().contains(errorMessage));
            System.out.println("Error message contains 'Your username is invalid' words.");
        } catch (Exception e) {
            System.out.println("Error message does NOT contains " + errorMessage + " words.");
            e.printStackTrace();
        }

        driver.quit();
    }

    @Test
    public void incorrectPassword(){

        System.out.println("Starting Incorrect Password Test");

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(url);

        WebElement userNameField = driver.findElement(By.id("username"));
        userNameField.sendKeys("tomsmith");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("WrongPassword!");

        sleep(2);

        WebElement loginButton  = driver.findElement(By.xpath("//button/i[contains(text(), 'Login')]"));
        loginButton.click();

        String errorMessage = "Your password is invalid";
        WebElement message = driver.findElement(By.xpath("//div[@id='flash']"));

        try {
            Assert.assertTrue(message.getText().contains(errorMessage));
            System.out.println("Error message contains 'Your password is invalid' words.");
        } catch (Exception e) {
            System.out.println("Error message does NOT contains " + errorMessage + " words.");
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
