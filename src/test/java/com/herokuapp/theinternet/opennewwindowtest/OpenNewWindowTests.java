package com.herokuapp.theinternet.opennewwindowtest;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.OpenNewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenNewWindowTests extends TestUtilities {

    @Test
    public void openNewWindowTest(){
        log.info("Starting OpenNewWindow test");

        String expectedLinkText = "Click Here";
        String actualLinkText;

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        OpenNewWindowPage openNewWindowPage = welcomePageObject.multipleWindowLink();
        actualLinkText = openNewWindowPage.getLinkText();

        Assert.assertEquals(actualLinkText, expectedLinkText, "Link text not match with expected");

        openNewWindowPage.openNewWindow();
        NewWindowPage newWindowPage = openNewWindowPage.switchToNewWindowPage();

        sleep(3);
        String pageSource = newWindowPage.getCurrentPageSource();
        Assert.assertTrue(pageSource.contains("New Window"), "NewWindow page source does not match");
    }

}
