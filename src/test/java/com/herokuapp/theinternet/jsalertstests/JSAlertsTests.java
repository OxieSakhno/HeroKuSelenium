package com.herokuapp.theinternet.jsalertstests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JSAlertsPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JSAlertsTests extends TestUtilities {

    @Test
    public void jsAlertTest(){
        log.info("Starting jsAlert Test");

        String expectedText = "I am a JS Alert";
        String expectedResultText = "You successfully clicked an alert";

        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        JSAlertsPage alertsPage = welcomePage.jsAlertsLink();
        alertsPage.triggerAlert();
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, expectedText, "Alert text does not match to expected");
        alertsPage.acceptAlert();
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, expectedResultText, "Result text does not match to expected");

    }

    @Test
    public void jsConfirmOkTest(){
        log.info("Starting jsConfirm -> 'Ok' Test");
        String expected = "I am a JS Confirm";
        String expectedResultText = "You clicked: Ok";

        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        JSAlertsPage alertsPage = welcomePage.jsAlertsLink();
        alertsPage.triggerConfirm();
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, expected, "Message in Alert not matched");
        alertsPage.acceptAlert();
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, expectedResultText, "Result Text message not matched");
    }


    @Test
    public void jsConfirmDismissTest(){
        log.info("Starting jsConfirm -> 'Cancel' Test");
        String expected = "I am a JS Confirm";
        String expectedResultText = "You clicked: Cancel";

        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        JSAlertsPage alertsPage = welcomePage.jsAlertsLink();
        alertsPage.triggerConfirm();
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, expected, "Message in Alert not matched");
        alertsPage.dismissAlert();
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, expectedResultText, "Result Text message not matched");

    }


    @Parameters({"text"})
    @Test
    public void jsPromptTest(String text){
        log.info("Starting jsPrompt -> 'OK' Test");
        String expected = "I am a JS prompt";
        String expectedResultText = "You entered: Hello Alert!";

        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        JSAlertsPage alertsPage = welcomePage.jsAlertsLink();
        alertsPage.triggerPrompt();
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, expected, "Message in Alert not matched");
        alertsPage.typeToPromptAlertField(text);
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, expectedResultText, "Result Text message not matched");
    }


    @Test
    public void jsPromptCancelTest(){
        log.info("Starting jsPrompt -> 'Cancel' Test");
        String expected = "I am a JS prompt";
        String expectedResultText = "You entered: null";

        WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
        welcomePage.openPage();
        JSAlertsPage alertsPage = welcomePage.jsAlertsLink();
        alertsPage.triggerPrompt();
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, expected, "Message in Alert not matched");
        alertsPage.dismissAlert();
        String resultText = alertsPage.getResultText();
        Assert.assertEquals(resultText, expectedResultText, "Result Text message not matched");
    }

}
