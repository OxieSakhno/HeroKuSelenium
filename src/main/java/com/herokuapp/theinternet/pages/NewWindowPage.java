package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class NewWindowPage extends BasePageObject{
    private String title;

    public NewWindowPage(WebDriver driver, Logger log){
        super(driver, log);
        this.title = driver.getTitle();
    }
}
