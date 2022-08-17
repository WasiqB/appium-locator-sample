package com.github.wasiqb.appium.core;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.AVD;
import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.ESPRESSO;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;
import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.FULL_RESET;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static java.lang.System.getenv;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManager {
    private static final String ANDROID = "Android";
    private static final String IOS     = "iOS";

    public static DriverManager getInstance () {
        return new DriverManager ();
    }

    private AppiumDriverLocalService service;

    public AndroidDriver<MobileElement> getAndroidEspressoCloudDriver () throws MalformedURLException {
        return new AndroidDriver<> (getUrl (), getAndroidEspressoCloudOptions ());
    }

    public AndroidDriver<MobileElement> getAndroidEspressoDriver () {
        startServer ();
        return new AndroidDriver<> (this.service.getUrl (), getAndroidEspressoOptions ());
    }

    public AndroidDriver<MobileElement> getAndroidUiAutomatorDriver () throws MalformedURLException {
        return new AndroidDriver<> (getUrl (), getAndroidUiAutomatorOptions ());
    }

    public IOSDriver<MobileElement> getIosDriver () throws MalformedURLException {
        return new IOSDriver<> (getUrl (), getIosOptions ());
    }

    public void stopServer () {
        if (this.service != null && this.service.isRunning ()) {
            this.service.stop ();
        }
    }

    private Capabilities getAndroidEspressoCloudOptions () {
        final var capabilities = new DesiredCapabilities ();
        capabilities.setCapability (PLATFORM_NAME, ANDROID);
        capabilities.setCapability (PLATFORM_VERSION, "11");
        capabilities.setCapability (DEVICE_NAME, "Pixel 5");
        capabilities.setCapability (AUTOMATION_NAME, ESPRESSO);
        capabilities.setCapability (APP, getenv ("LT_APP_ANDROID_2"));
        setCloudCapabilities (capabilities, ANDROID);
        return capabilities;
    }

    private Capabilities getAndroidEspressoOptions () {
        final var capabilities = new DesiredCapabilities ();
        capabilities.setCapability (PLATFORM_NAME, ANDROID);
        capabilities.setCapability (PLATFORM_VERSION, "10");
        capabilities.setCapability (DEVICE_NAME, "Pixel_5");
        capabilities.setCapability (AUTOMATION_NAME, ESPRESSO);
        capabilities.setCapability (APP, new File ("src/test/resources/apps/ApiDemos-debug.apk").getAbsolutePath ());
        capabilities.setCapability (AVD, "Pixel_5");
        capabilities.setCapability (FULL_RESET, true);
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
        capabilities.setCapability ("build",
            format ("TestNG {0} Sample Build for {1}", platform, capabilities.getCapability (AUTOMATION_NAME)));
        capabilities.setCapability ("name", format ("{0} Test Case", platform));
        capabilities.setCapability ("visual", true);
        capabilities.setCapability ("network", true);
        capabilities.setCapability ("video", true);
        capabilities.setCapability ("autoGrantPermissions", true);
        capabilities.setCapability ("autoAcceptAlerts", true);
        capabilities.setCapability ("isRealMobile", true);
        capabilities.setCapability ("console", true);
    }

    private void startServer () {
        final AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        builder.withIPAddress ("127.0.0.1")
            .usingPort (4723)
            .withAppiumJS (
                new File ("/Users/wasiqbhamla/.nvm/versions/node/v16.16.0/lib/node_modules/appium/build/lib/main.js"))
            .withLogFile (new File (System.getProperty ("user.dir") + "/logs/appium.log"))
            .withArgument (GeneralServerFlag.LOG_LEVEL, "info")
            .withArgument (GeneralServerFlag.SESSION_OVERRIDE);
        this.service = AppiumDriverLocalService.buildService (builder);
        this.service.start ();
    }
}
