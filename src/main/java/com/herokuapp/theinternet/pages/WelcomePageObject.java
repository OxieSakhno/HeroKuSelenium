package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject{

    private String pageUrl = "https://the-internet.herokuapp.com/";

    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By checkboxesLinkLocator = By.linkText("Checkboxes");
    private By dropdownLinkLocator = By.linkText("Dropdown");
    private By alertsLinkLocator = By.linkText("JavaScript Alerts");
    private By multipleWindowsLocator = By.linkText("Multiple Windows");
    private By wysiwygEditorLocator = By.linkText("WYSIWYG Editor");

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

    /** Open DropdownListPage by clicking on Dropdown link */
    public DropdownListPage dropdownLink(){
        log.info("Clicking Dropdown link on Welcome Page");
        click(dropdownLinkLocator);
        return new DropdownListPage(driver, log);
    }

    /** Open JSAlertsPage by clicking on JavaScript Alerts link*/
    public JSAlertsPage jsAlertsLink(){
        log.info("Clicking JavaScript Alert link on Welcome Page");
        click(alertsLinkLocator);
        return new JSAlertsPage(driver, log);
    }

    /** Open OpeningNewWindow page by clicking on MultipleWindows link */
    public OpenNewWindowPage multipleWindowLink(){
        log.info("Clicking Multiple Windows link on Welcome Page");
        click(multipleWindowsLocator);
        return new OpenNewWindowPage(driver, log);
    }

    /** Open WYSIWYGPage by clicking WYSIWYG Editor link */
    public WysiwygEditorPage clickWysiwygEditorLink() {
        log.info("Clicking WYSIWYG Editor link on Welcome Page");
        click(wysiwygEditorLocator);
        return new WysiwygEditorPage(driver, log);
    }

}
