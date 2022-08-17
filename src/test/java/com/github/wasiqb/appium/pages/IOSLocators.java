package com.github.wasiqb.appium.pages;

import io.appium.java_client.MobileBy;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class IOSLocators {
    private final By colorByAccessibilityId = MobileBy.AccessibilityId ("color");   // iOS
    private final By colorByClassChain      = MobileBy.iOSClassChain (
        "**/XCUIElementTypeButton[`label == \"Colour\"`]");
    private final By colorByPredicate       = MobileBy.iOSNsPredicateString (
        "label == \"Colour\" AND name == \"color\"");
}
