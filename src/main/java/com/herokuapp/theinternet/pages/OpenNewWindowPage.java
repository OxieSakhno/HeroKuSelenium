package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenNewWindowPage extends BasePageObject{
    private By newWindowPageLink = By.linkText("Click Here");
    private String title = "New Window";

    public OpenNewWindowPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    public void openNewWindow(){
        log.info("Clicking Click Here link on OpenNewWindowPage");
        click(newWindowPageLink);
    }

    public String getLinkText(){
        log.info("Taking link text");
        return driver.findElement(newWindowPageLink).getText();
    }

    public NewWindowPage switchToNewWindowPage(){
        switchToWindowWithTitle(title);
        return new NewWindowPage(driver, log);
    }

}
