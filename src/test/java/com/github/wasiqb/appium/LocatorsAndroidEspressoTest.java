package com.github.wasiqb.appium;

import static org.testng.Assert.assertTrue;

import com.github.wasiqb.appium.pages.AndroidLocators;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsAndroidEspressoTest extends BaseTest {
    private AndroidLocators page;

    @BeforeClass (alwaysRun = true)
    public void setupClass () {
        this.driver = DRIVER_MANAGER.getAndroidEspressoDriver ();
        this.page = new AndroidLocators ();
    }

    @Test
    public void testByDataMatcher () {
        assertTrue (getElement (this.page.getAppItemDataMatcher (), "Data Matcher").isDisplayed ());
    }

    @Test
    public void testByViewMatcher () {
        assertTrue (getElement (this.page.getAnimationItemViewMatcher (), "View Matcher").isDisplayed ());
    }
}
