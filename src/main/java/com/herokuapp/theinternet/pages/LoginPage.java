package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject{

    private String pageUrl = "https://the-internet.herokuapp.com/login";

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.name("password");
    private By loginButtonLocator = By.tagName("button");
    private By message = By.xpath("//*[@id='flash']");

    /** Page Constructor */
    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Returns pageUrL from PageObject */
    public String getPageUrl(){
        return pageUrl;
    }

    /** Fills out data and logs in */
    public SecureAreaPage logIn(String username, String password){
        log.info("Login using ["+ username + "] and password ["+ password +"]");
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginButtonLocator);
        return new SecureAreaPage(driver, log);
    }

    /** Returns text of confirmation message */
    public String getErrorMessageText(){
        return find(message).getText();
    }
}
