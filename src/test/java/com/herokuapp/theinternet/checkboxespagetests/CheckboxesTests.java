package com.herokuapp.theinternet.checkboxespagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTests extends TestUtilities {

    @Test(priority = 1)
    public void selectingTwoCheckboxesTest(){
        log.info("Starting selectingTwoCheckboxesTest");

        //open main page
        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        sleep(2);

        //Click on Checkboxes link
        CheckboxesPage checkboxesPage = welcomePage.clickCheckboxesLink();

        //Select all checkboxes
        sleep(2);
        checkboxesPage.selectAllCheckboxes();
        sleep(2);

        //Verify all checkboxes are checked
        Assert.assertTrue(checkboxesPage.areAllCheckboxesChecked(), "Not all checkboxes are checked");
    }

    @Test(priority = 2)
    public void defaultCheckboxStateTest(){
        log.info("Starting defaultCheckboxStateTest");

        //open main page
        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        sleep(2);

        //Click on Checkbox link
        CheckboxesPage checkboxesPage = welcomePage.clickCheckboxesLink();
        sleep(2);

        //Find default checkbox element and verify it is selected or not
        boolean checkboxSelected = checkboxesPage.isDefaultCheckboxSelected();
        Assert.assertTrue(checkboxSelected, "Default checkbox is not selected");
        log.info("Default checkbox is selected");
    }

}
