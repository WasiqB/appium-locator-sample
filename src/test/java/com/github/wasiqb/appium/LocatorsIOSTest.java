package com.github.wasiqb.appium;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import com.github.wasiqb.appium.pages.IOSLocators;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsIOSTest extends BaseTest {
    private IOSLocators page;

    @BeforeClass (alwaysRun = true)
    public void setupClass () throws MalformedURLException {
        this.driver = DRIVER_MANAGER.getIosDriver ();
        this.page = new IOSLocators ();
    }

    @Test
    public void testByAccessibilityId () {
        assertTrue (getElement (this.page.getColorByAccessibilityId (), "Accessibility Id").isDisplayed ());
    }

    @Test
    public void testByClassChain () {
        assertTrue (getElement (this.page.getColorByClassChain (), "Class Chain").isDisplayed ());
    }

    @Test
    public void testByPredicate () {
        assertTrue (getElement (this.page.getColorByPredicate (), "Predicate").isDisplayed ());
    }
}
