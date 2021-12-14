package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject{

    private String pageUrl = "https://the-internet.herokuapp.com/";

    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By checkboxesLinkLocator = By.linkText("Checkboxes");

    /** Page Constructor */
    public WelcomePageObject(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Open WelcomePage in browser */
    public void openPage(){
        log.info("Opening page: " + pageUrl);
        openURL(pageUrl);
        log.info("Page is opened");
    }

    /** Open LoginPage by clicking on Form Authentication link */
    public LoginPage clickFormAuthenticationLink(){
        log.info("Clicking Form Authentication link on Welcome Page");
        click(formAuthenticationLinkLocator);
        return new LoginPage(driver, log);
    }

    /** Open CheckboxesPage by clicking on Checkboxes link */
    public CheckboxesPage clickCheckboxesLink(){
        log.info("Clicking Checkboxes link on Welcome Page");
        click(checkboxesLinkLocator);
        return new CheckboxesPage(driver, log);
    }
}
