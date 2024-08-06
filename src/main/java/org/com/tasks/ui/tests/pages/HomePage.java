package org.com.tasks.ui.tests.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class HomePage extends BasePage {

    @FindBy(xpath = "(//a[contains(text(),'Trading')])[1]")
    private WebElement tradingLink;
    @FindBy(xpath = "//div[@id='cookieModal']//button[contains(text(),'ACCEPT ALL')]")
    private WebElement acceptAllButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}