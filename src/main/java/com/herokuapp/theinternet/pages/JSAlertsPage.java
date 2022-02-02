package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JSAlertsPage extends BasePageObject{

    private By alertButton = By.xpath("//button[contains(text(), 'Click for JS Alert')]");
    private By confirmButton = By.xpath("//button[contains(text(), 'Click for JS Confirm')]");;
    private By promptButton = By.xpath("//button[contains(text(), 'Click for JS Prompt')]");;
    private By resultElement = By.xpath("//p[@id='result']");

    public JSAlertsPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    /** Clicks on JSAlert button */
    public void triggerAlert(){
        log.info("Clicking on 'Click for JS Alert' button");
        click(alertButton);
    }

    /** Clicks on JSConfirm button */
    public void triggerConfirm(){
        log.info("Clicking on 'Click for JS Confirm' button");
        click(confirmButton);
    }

    /** Clicks on JS Prompt button */
    public void triggerPrompt(){
        log.info("Clicking on 'Click for JS Prompt' button");
        click(promptButton);
    }

    /** Switch to Alert and get it's message */
    public String getAlertText(){
        log.info("Switching to Alert and get it's text");
        Alert alert = switchToAlert();
        return alert.getText();
    }

    /** Switch to Alert and press 'Ok' */
    public void acceptAlert(){
        log.info("Switching to Alert and press 'Ok'");
        Alert alert = switchToAlert();
        alert.accept();
    }

    /** Switch to Alert and press 'Cancel' */
    public void dismissAlert(){
        log.info("Switching to Alert and press 'Cancel'");
        Alert alert = switchToAlert();
        alert.dismiss();
    }


    /* Get's text from Result field */
    public String getResultText(){
        log.info("Getting text from Result field");
        return find(resultElement).getText();
    }

    public void typeToPromptAlertField(String text){
        log.info("Typing text into prompt field and pressing OK");
        Alert alert = switchToAlert();
        alert.sendKeys(text);
        alert.accept();
    }

}
