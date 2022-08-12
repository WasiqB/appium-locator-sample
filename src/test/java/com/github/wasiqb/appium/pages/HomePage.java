package com.github.wasiqb.appium.pages;

import io.appium.java_client.MobileBy;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class HomePage {
    private final By colorByAccessibilityId = MobileBy.AccessibilityId ("color");   // iOS
    private final By colorByClassChain      = MobileBy.iOSClassChain (
        "**/XCUIElementTypeButton[`label == \"Colour\"`]");
    private final By colorByClassName       = MobileBy.className ("android.widget.Button");
    private final By colorById              = MobileBy.id ("color");
    private final By colorByPredicate       = MobileBy.iOSNsPredicateString (
        "label == \"Colour\" AND name == \"color\"");
    private final By colorByUiSelector      = MobileBy.AndroidUIAutomator ("new UiSelector().text(\"COLOR\")");
    private final By colorByXpath           = MobileBy.xpath (".//android.widget.Button[@text='COLOR']");
}
