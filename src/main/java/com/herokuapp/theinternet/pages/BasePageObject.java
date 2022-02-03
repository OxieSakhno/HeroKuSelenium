package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;

    /** Page constructor */
    public BasePageObject(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    /** Open page with given URL */
    protected void openURL(String pageUrl){
        driver.get(pageUrl);
    }

    /** Find element using given locator */
    protected WebElement find(By locator){
        waitForVisibilityOf(locator, 5);
        return driver.findElement(locator);
    }

    /** Finds all elements using given locator */
    protected List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    /** Click on element with given locator when it visible */
    protected void click(By locator){
        //waitForVisibilityOf(locator, 5); // Redundant method. find() already has waitForVisibility
        find(locator).click();
    }

    /** Type given text into element with given locator */
    protected void type(String text, By locator){
        // waitForVisibilityOf(locator, 5); //Same as click()
        find(locator).sendKeys(text);
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     * This func is a Wrapper for some particular Condition that we'll pass to wait.until(ourCondition)
     * and than use in some particular func on the page
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds){
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    /**
     * Wait for given number of seconds for element with given locator to be visible on the page
     */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds){
        int attempts = 0;
        while (attempts < 2){
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                        break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Exception caught " + e.getMessage());
            }
            attempts++;
        }
    }

    /** Gets current URL */
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /** Wait for Alert present and then switch to it */
    protected Alert switchToAlert(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    /** Returns title of the page */
    private String getCurrentPageTitle(){
        return driver.getTitle();
    }

    /** Returns page sours */
    public String getCurrentPageSource(){
        return driver.getPageSource();
    }

    /** Switches to Page with given title */
    protected void switchToWindowWithTitle(String expectedTitle){
        //Switching to new window
        String parent = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> windowsIterator = allWindows.iterator();

        while(windowsIterator.hasNext()){
            String windowHandle = windowsIterator.next().toString();
            if(!windowHandle.equals(parent)){
                driver.switchTo().window(windowHandle);
                if(getCurrentPageTitle().equals(expectedTitle)){
                    break;
                }
            }
        }
    }

}
