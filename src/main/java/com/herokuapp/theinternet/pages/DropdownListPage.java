package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownListPage extends BasePageObject{

    private By dropdownList = By.xpath("//select[@id='dropdown']");
    private WebElement dropdownMenu = find(dropdownList);
    Select dropdown = new Select(dropdownMenu);


    public DropdownListPage(WebDriver driver, Logger log){
        super(driver, log);
    }

    /** Selects given option from dropdown list */
    public void selectOption(int i){
        log.info("Selecting option " + i + " from dropdown menu");
        //first way to use Select class
        //dropdown.selectByIndex(i);
        //second way
        dropdown.selectByValue("" + i);
        //third
        //dropdown.selectByVisibleText("Option " + i);
    }


    /** This method returns selected option from dropdown as a String */
    public String getSelectedOption(){
        return dropdown.getFirstSelectedOption().getText();
    }

}
