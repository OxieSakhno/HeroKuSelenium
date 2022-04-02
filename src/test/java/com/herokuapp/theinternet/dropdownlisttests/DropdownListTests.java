package com.herokuapp.theinternet.dropdownlisttests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DropdownListPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropdownListTests extends TestUtilities {

    @Test
    public void selectOptionTest(){
        log.info("Starting selectOptionTwo test");

        //open welcome page
        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();

        //click on Dropdown link
        DropdownListPage dropdownListPage = welcomePage.dropdownLink();

        //select option two from dropdown list
        dropdownListPage.selectOption(2);
        sleep(5);

        //verify that Option 2 is selected
        Assert.assertEquals(dropdownListPage.getSelectedOption(), "Option 2", "Option 2 is not selected." +
                "\nInstead selected - " + dropdownListPage.getSelectedOption());
    }
}
