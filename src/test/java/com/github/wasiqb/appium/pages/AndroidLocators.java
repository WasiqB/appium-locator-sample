package com.github.wasiqb.appium.pages;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.json.Json;

@Getter
public class AndroidLocators {
    private final By animationItemViewMatcher = MobileBy.androidViewMatcher (new Json ().toJson (
        ImmutableMap.of ("name", "withText", "args", "Animation", "class",
            "androidx.test.espresso.matcher.ViewMatchers")));
    private final By appItemDataMatcher       = MobileBy.androidDataMatcher (
        new Json ().toJson (ImmutableMap.of ("name", "hasEntry", "args", ImmutableList.of ("title", "App"))));
    private final By colorByClassName         = MobileBy.className ("android.widget.Button");
    private final By colorById                = MobileBy.id ("color");
    private final By colorByUiSelector        = MobileBy.AndroidUIAutomator ("new UiSelector().text(\"COLOR\")");
    private final By colorByXpath             = MobileBy.xpath (".//android.widget.Button[@text='COLOR']");
}
