package com.github.wasiqb.appium;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import com.github.wasiqb.appium.pages.AndroidLocators;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsAndroidEspressoCloudTest extends BaseTest {
    private AndroidLocators page;

    @BeforeClass (alwaysRun = true)
    public void setupClass () throws MalformedURLException {
        this.driver = DRIVER_MANAGER.getAndroidEspressoCloudDriver ();
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
