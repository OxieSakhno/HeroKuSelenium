package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject{

    private String pageUrl = "https://the-internet.herokuapp.com/secure";

    private By logoutButton = By.xpath("//div[@id='content']//i[contains(text(), 'Logout')]/..");
    private By message = By.xpath("//*[@id='flash']");


    /** Page Constructor */
    public SecureAreaPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    /** Returns pageUrL from PageObject */
    public String getPageUrl(){
        return pageUrl;
    }

    /** Returns True or False when button visible or not */
    public boolean isLogoutButtonVisible(){
        return find(logoutButton).isDisplayed();
    }

    /** Returns text of confirmation message */
    public String getSuccessMessageText(){
        return find(message).getText();
    }
}
