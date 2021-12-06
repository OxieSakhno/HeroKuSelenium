package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    String url = "http://the-internet.herokuapp.com/login";
    String login = "tomsmith";
    String password = "SuperSecretPassword!";
    String secureAreaPageUrl = "http://the-internet.herokuapp.com/secure";


    @Test
    public void loginTest(){

        //create driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //open test page

        driver.get(url);
        sleep(2);

        //enter username
        WebElement userNameField = driver.findElement(By.xpath("//*[@id='username']"));
        userNameField.sendKeys(login);

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
            WebElement successLoginMessage = driver.findElement(By.xpath("//div[@id='flash'][contains(text(), 'You logged into')]"));
            Assert.assertTrue(successLoginMessage.getText().contains("You logged into"));
            System.out.println("'You logged into' message is displayed.");
        } catch (Exception e) {
            System.out.println("'You logged into' message is NOT displayed.");
            e.printStackTrace();
        }


        //close driver
        driver.quit();
    }

    private void sleep(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
