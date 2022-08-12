package com.github.wasiqb.appium.core;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.ESPRESSO;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static java.lang.System.getenv;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {
    private static final String ANDROID = "Android";
    private static final String IOS     = "iOS";

    public static DriverManager getInstance () {
        return new DriverManager ();
    }

    public AndroidDriver<MobileElement> getAndroidEspressoDriver () throws MalformedURLException {
        return new AndroidDriver<> (getUrl (), getAndroidEspressoOptions ());
    }

    public AndroidDriver<MobileElement> getAndroidUiAutomatorDriver () throws MalformedURLException {
        return new AndroidDriver<> (getUrl (), getAndroidUiAutomatorOptions ());
    }

    public IOSDriver<MobileElement> getIosDriver () throws MalformedURLException {
        return new IOSDriver<> (getUrl (), getIosOptions ());
    }

    private Capabilities getAndroidEspressoOptions () {
        final var capabilities = new DesiredCapabilities ();
        capabilities.setCapability (PLATFORM_NAME, ANDROID);
        capabilities.setCapability (PLATFORM_VERSION, "11");
        capabilities.setCapability (DEVICE_NAME, "Pixel 5");
        capabilities.setCapability (AUTOMATION_NAME, ESPRESSO);
        capabilities.setCapability (APP, getenv ("LT_APP_ANDROID_2"));
        setCloudCapabilities (capabilities, ANDROID);
        return capabilities;
    }

    private Capabilities getAndroidUiAutomatorOptions () {
        final var capabilities = new DesiredCapabilities ();
        capabilities.setCapability (PLATFORM_NAME, ANDROID);
        capabilities.setCapability (PLATFORM_VERSION, "11");
        capabilities.setCapability (DEVICE_NAME, "Pixel 5");
        capabilities.setCapability (AUTOMATION_NAME, ANDROID_UIAUTOMATOR2);
        capabilities.setCapability (APP, getenv ("LT_APP_ANDROID"));
        setCloudCapabilities (capabilities, ANDROID);
        return capabilities;
    }

    private Capabilities getIosOptions () {
        final var capabilities = new DesiredCapabilities ();
        capabilities.setCapability (PLATFORM_NAME, IOS);
        capabilities.setCapability (PLATFORM_VERSION, "15");
        capabilities.setCapability (DEVICE_NAME, "iPhone 13 Pro");
        capabilities.setCapability (AUTOMATION_NAME, IOS_XCUI_TEST);
        capabilities.setCapability (APP, getenv ("LT_APP_IOS"));
        setCloudCapabilities (capabilities, IOS);
        return capabilities;
    }

    private URL getUrl () throws MalformedURLException {
        final var LT_KEY = System.getenv ("LT_ACCESS_KEY");
        final var LT_USER = System.getenv ("LT_USERNAME");
        return new URL (format ("https://{0}:{1}@mobile-hub.lambdatest.com/wd/hub", LT_USER, LT_KEY));
    }

    private void setCloudCapabilities (final DesiredCapabilities capabilities, final String platform) {
        capabilities.setCapability ("project", "LambdaTest project");
        capabilities.setCapability ("build", format ("TestNG {0} Sample Build", platform));
        capabilities.setCapability ("name", format ("{0} Test Case", platform));
        capabilities.setCapability ("visual", true);
        capabilities.setCapability ("network", true);
        capabilities.setCapability ("video", true);
        capabilities.setCapability ("autoGrantPermissions", true);
        capabilities.setCapability ("autoAcceptAlerts", true);
        capabilities.setCapability ("isRealMobile", true);
        capabilities.setCapability ("console", true);
    }
}
