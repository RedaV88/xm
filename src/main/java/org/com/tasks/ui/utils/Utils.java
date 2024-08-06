package org.com.tasks.ui.utils;

import org.awaitility.Awaitility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static org.com.common.BaseTest.config;


public class Utils {

    public static void waitForElement(WebElement elementToWaitFor) {
        Awaitility.await()
                .pollInterval(2, TimeUnit.SECONDS)
                .atMost(config.MAX_WAIT_TIME(), TimeUnit.SECONDS)
                .until(() -> isElementClickable(elementToWaitFor));
    }

    public static void waitForUrlToBe(WebDriver driver, String unexpectedUrl) {
        Awaitility.await()
                .pollInterval(2, TimeUnit.SECONDS)
                .atMost(config.MAX_WAIT_TIME(), TimeUnit.SECONDS)
                .until(() -> driver.getCurrentUrl().equals(unexpectedUrl));
    }

    public static boolean isElementClickable(WebElement element) {
        try {
            return element != null && element.isEnabled() && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitAndClick(WebElement element, WebDriver driver) {
        Utils.waitForElement(element);
        clickWithJs(element, driver);
    }

    public static void clickWithJs(WebElement webElement, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }
}
