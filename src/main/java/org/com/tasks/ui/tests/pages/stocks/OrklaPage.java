package org.com.tasks.ui.tests.pages.stocks;

import lombok.Data;
import org.com.tasks.ui.tests.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class OrklaPage extends BasePage {

    String marginRequirementTitle = "margin_requirement__value";
    String spreadsAsLowAsTitle = "spreads_as_low_as__value";
    String minMaxTradeSizeTitle = "min_max_trade_size__value";
    String swapValueInMarginCurrencyLongTitle = "swap_value_in_margin_currency_long__value";
    String swapValueInMarginCurrencyShortTitle = "swap_value_in_margin_currency_short__value";
    String limitAndStopLevelsTitle = "limit_and_stop_levels__title";

    @FindBy(xpath = "//table[@class='table pull-left']")
    private WebElement leftTable;
    @FindBy(xpath = "//table[@class='table pull-right']")
    private WebElement rightTable;

    public OrklaPage(WebDriver driver) {
        super(driver);
    }
}