package com.github.wasiqb.appium;

import static com.github.wasiqb.appium.core.DriverManager.getInstance;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorsAndroidEspressoTest {
    private WebDriver driver;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        this.driver = getInstance ().getAndroidEspressoDriver ();
    }

    @AfterClass
    public void tearDownClass () {
        this.driver.quit ();
    }

    @Test
    public void testById () {

    }
}
