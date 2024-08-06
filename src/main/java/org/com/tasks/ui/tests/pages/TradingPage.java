package org.com.tasks.ui.tests.pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class TradingPage extends BasePage {

    @FindBy(xpath = "//li[@class='main_nav_trading selected']")
    private WebElement tradingLinkSelected;
    @FindBy(xpath = "//li[@class='menu-stocks']/a")
    private WebElement stocksLink;

    public TradingPage(WebDriver driver) {
        super(driver);
    }
}