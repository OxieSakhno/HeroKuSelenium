package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExceptionsTests {
    private WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    private void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
        driver.quit();
    }

    @Test(priority = 1)
    public void notVisibleTest(){
        System.out.println("\nElement Not Visible Test is started.");

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 7);
        WebElement finishElement= driver.findElement(By.id("finish"));

        wait.until(ExpectedConditions.visibilityOf(finishElement));
        String finishText = finishElement.getText();

        Assert.assertTrue(finishText.contains("Hello World!"), "Finish text " + finishText);
    }

    @Test(priority = 2)
    public void timeoutTest(){
        System.out.println("\nTimeout Test is started.");

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        WebElement finishElement= driver.findElement(By.id("finish"));
        WebDriverWait wait = new WebDriverWait(driver, 7);

        try {
            wait.until(ExpectedConditions.visibilityOf(finishElement));
        } catch (TimeoutException exception) {
            System.out.println("Exception caught: " + exception.getMessage());
        }

        String finishText = finishElement.getText();

        Assert.assertTrue(finishText.contains("Hello World!"), "Finish text " + finishText);
    }

    @Test(priority = 3)
    public void noSuchElementTest(){
        System.out.println("\nNo Such Element Test is started.");

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        /*Assert.assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"), "Hello World!")),
                    "Expected text not match to actual.");
        System.out.println("Expected text matched to actual.");*/

        try {
            String hello = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish"))).getText();
            System.out.println("Element contains text: " + hello);
        } catch (TimeoutException exception) {
            System.out.println("Exception caught: " + exception.getMessage());
        }
    }


    @Test(priority = 4)
    public void staleElementTest(){
        System.out.println("\nStale Element Test is started.");
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement checkbox = driver.findElement(By.id("checkbox"));
        //checkbox.click();
        WebElement removeButton = driver.findElement(By.xpath("//button[contains(text(), 'Remove')]"));
        WebDriverWait wait = new WebDriverWait(driver, 10);

        removeButton.click();
//        wait.until(ExpectedConditions.invisibilityOf(checkbox));
//        Assert.assertFalse(checkbox.isDisplayed(), "Check box is still visible.");

//        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkbox)));

        try {
            Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)), "Checkbox shouldn't be visible.");
            System.out.println("Checkbox has disappeared.");
        } catch (Exception e) {
            System.out.println("Exception text: " + e.getMessage());;
        }

        WebElement addButton = driver.findElement(By.xpath("//button[contains(text(), 'Add')]"));
        addButton.click();

        //reAssign reference to the new checkbox object
        checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
        Assert.assertTrue(checkbox.isDisplayed(), "Error. Checkbox is not visible.");
        System.out.println("Checkbox is displayed again. Hooray!");
    }


    @Test
    public void disabledElementTest(){
        System.out.println("\nDisabled Element Test is started.");
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        String text = "Hello World!";

        WebElement enableButton = driver.findElement(By.xpath("//button[contains(text(), 'Enable')]"));
        WebElement enableField = driver.findElement(By.xpath("//form[@id='input-example']/input"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        enableButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(enableField));
        enableField.sendKeys(text);

        String textFromField = enableField.getAttribute("value");
        Assert.assertEquals(textFromField, text);
        System.out.println("Entered value is: " + text
                + "\nRetrieved value is: " + textFromField);
    }
}