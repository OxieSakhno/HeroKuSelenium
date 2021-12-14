package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePageObject{

    private String pageUrl = "https://the-internet.herokuapp.com/checkboxes";
    private By checkbox = By.xpath("//form[@id='checkboxes']/input");
    private By defaultCheckbox = By.xpath("//form[@id='checkboxes']/input[2]");

    public CheckboxesPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    //*Gets list of all checkboxes and checks those if unchecked
    public void selectAllCheckboxes(){
        log.info("Checking all unchecked checkboxes");
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
    }

    /**
     * Verify all available checkboxes checked,
     * If at least one unchecked - return false
     */
    public boolean areAllCheckboxesChecked(){
        log.info("Verifying that all checkboxes are checked.");
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement checkbox : checkboxes){
            if (!checkbox.isSelected()){
                return false;
            }
        }
        return true;
    }

    //** Verify defaultCheckbox is selected */
    public boolean isDefaultCheckboxSelected(){
        log.info("Verify that Default checkbox is checked.");
        return (find(defaultCheckbox).isSelected());
    }

}
