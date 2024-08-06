package org.com.tasks.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {
    private WebDriver driver;

    public WebDriver initializeChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        return driver;
    }

    public WebDriver initializeFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }

    public WebDriver initializeSafariDriver() {
        WebDriver driver = new SafariDriver();
        return driver;
    }


    public WebDriver initializeDriver(BrowserType browser) {
        switch (browser) {
            case CHROME:
                return initializeChromeDriver();
            case FIREFOX:
                return initializeFirefoxDriver();
            case SAFARI:
                return initializeSafariDriver();
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browser);
        }
    }

    public void setWindowSize(WebDriver driver, WindowSize size) {
        switch (size) {
            case MAXIMIZE:
                driver.manage().window().maximize();
                break;
            case SIZE_1024x768:
                driver.manage().window().setSize(new Dimension(1024, 768));
                break;
            case SIZE_800x600:
                driver.manage().window().setSize(new Dimension(800, 600));
                break;
            default:
                throw new IllegalArgumentException("Unsupported window size: " + size);
        }
    }
}
