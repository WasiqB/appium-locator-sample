package com.github.wasiqb.appium;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import com.github.wasiqb.appium.pages.AndroidLocators;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsAndroidUiAutomatorTest extends BaseTest {
    private AndroidLocators page;

    @BeforeClass (alwaysRun = true)
    public void setupClass () throws MalformedURLException {
        this.driver = DRIVER_MANAGER.getAndroidUiAutomatorDriver ();
        this.page = new AndroidLocators ();
    }

    @Test
    public void testByClassName () {
        assertTrue (getElement (this.page.getColorByClassName (), "Class Name").isDisplayed ());
    }

    @Test
    public void testById () {
        assertTrue (getElement (this.page.getColorById (), "ID").isDisplayed ());
    }

    @Test
    public void testByUiSelector () {
        assertTrue (getElement (this.page.getColorByUiSelector (), "Ui Selector").isDisplayed ());
    }

    @Test
    public void testByXpath () {
        assertTrue (getElement (this.page.getColorByXpath (), "Xpath").isDisplayed ());
    }
}
