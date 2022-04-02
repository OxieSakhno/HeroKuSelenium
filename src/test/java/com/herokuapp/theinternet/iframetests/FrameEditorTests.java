package com.herokuapp.theinternet.iframetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import com.herokuapp.theinternet.pages.WysiwygEditorPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FrameEditorTests extends TestUtilities {

    @Test
    public void getFrameEditorTextTest() {
        log.info("Get Frame Editor's test");

        String expectedEditorText = "Your content goes here.";
        String actualEditorText;

        //open welcome page
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        //click wysiwyg editor link
        WysiwygEditorPage editorPage = welcomePageObject.clickWysiwygEditorLink();

        // switch to iFrame. Get editor's text
        actualEditorText = editorPage.getEditorText();

        //assert actual text and expected
        Assert.assertEquals(actualEditorText, expectedEditorText);
    }

}
