package com.github.wasiqb.appium;

import static java.lang.System.currentTimeMillis;
import static java.text.MessageFormat.format;

import com.github.wasiqb.appium.core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class BaseTest {
    protected static final DriverManager               DRIVER_MANAGER = DriverManager.getInstance ();
    protected              AppiumDriver<MobileElement> driver;

    @AfterClass (alwaysRun = true)
    public void tearDownClass () {
        this.driver.quit ();
        DRIVER_MANAGER.stopServer ();
    }

    protected MobileElement getElement (final By by, final String locatorType) {
        final long start = currentTimeMillis ();
        try {
            return this.driver.findElement (by);
        } finally {
            System.out.println (format ("Time taken by {0}: {1}ms", locatorType, currentTimeMillis () - start));
        }
    }
}
