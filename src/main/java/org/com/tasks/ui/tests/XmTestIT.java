package org.com.tasks.ui.tests;


import org.assertj.core.api.SoftAssertions;
import org.com.common.BaseTest;
import org.com.tasks.ui.BrowserType;
import org.com.tasks.ui.WebDriverFactory;
import org.com.tasks.ui.WindowSize;
import org.com.tasks.ui.tests.pages.HomePage;
import org.com.tasks.ui.tests.pages.StocksPage;
import org.com.tasks.ui.tests.pages.TradingPage;
import org.com.tasks.ui.tests.pages.stocks.OrklaPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.com.tasks.ui.utils.StocksUtil.getDataFromTable;
import static org.com.tasks.ui.utils.Utils.waitAndClick;
import static org.com.tasks.ui.utils.Utils.waitForUrlToBe;


public class XmTestIT extends BaseTest {

    WebDriverFactory factory = new WebDriverFactory();
    WebDriver driver = factory.initializeDriver(BrowserType.FIREFOX);

    @Before
    public void setUp() {
        factory.setWindowSize(driver, WindowSize.MAXIMIZE);
        driver.get(config.resourceLink());
    }

    @After
    public void closeSession(){
        driver.close();
    }

    @Test
    public void compareDataBetweenStocksTableAndTradingConditionsInfo() {
        var EXPECTED_TITLE = "XM - Global Broker in Forex, Stocks, Indices, Oil and Gold";
        var EXPECTED_STOCKS_URL = "https://www.xm.com/stocks";
        HomePage homePage = new HomePage(driver);
        driver.get(config.resourceLink());

        waitAndClick(homePage.getAcceptAllButton(), driver);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(driver.getTitle())
                .as("Home page title is not as expected")
                .isEqualTo(EXPECTED_TITLE);

        waitAndClick(homePage.getTradingLink(), driver);
        TradingPage tradingPage = new TradingPage(driver);
        softly.assertThat(tradingPage.getTradingLinkSelected().isDisplayed())
                .as("Trading link is not selected")
                .isTrue();

        waitAndClick(tradingPage.getStocksLink(), driver);

        waitForUrlToBe(driver, EXPECTED_STOCKS_URL);
        softly.assertThat(driver.getCurrentUrl())
                .as("URL is not as expected")
                .isEqualTo(EXPECTED_STOCKS_URL);

        StocksPage stocksPage = new StocksPage(driver);
        waitAndClick(stocksPage.getNorwayButton(), driver);
        waitAndClick(stocksPage.getNextTableButton(), driver);

        var minSpreadOnTable = getDataFromTable(stocksPage.getOrklaAsaRow(), stocksPage.getMinSpread());
        var minMaxTradeSizeOnTable = getDataFromTable(stocksPage.getOrklaAsaRow(), stocksPage.getMinMaxTradeSize());
        var marginRequirementOnTable = getDataFromTable(stocksPage.getOrklaAsaRow(), stocksPage.getMarginRequirement());
        var longSwapOnTable = getDataFromTable(stocksPage.getOrklaAsaRow(), stocksPage.getSwapLong());
        var shortSwapOnTable = getDataFromTable(stocksPage.getOrklaAsaRow(), stocksPage.getSwapShort());

        waitAndClick(stocksPage.getOrklaReadMore(), driver);
        OrklaPage orklaPage = new OrklaPage(driver);

        var minSpread = getDataFromTable(orklaPage.getRightTable(), orklaPage.getSpreadsAsLowAsTitle());
        var minMaxTradeSize = getDataFromTable(orklaPage.getRightTable(), orklaPage.getMinMaxTradeSizeTitle());
        var marginRequirement = getDataFromTable(orklaPage.getLeftTable(), orklaPage.getMarginRequirementTitle());
        var longSwap = getDataFromTable(orklaPage.getRightTable(), orklaPage.getSwapValueInMarginCurrencyLongTitle());
        var shortSwap = getDataFromTable(orklaPage.getRightTable(), orklaPage.getSwapValueInMarginCurrencyShortTitle());

        softly.assertThat(minSpreadOnTable)
                .as("minSpread is not as expected")
                .isEqualTo(minSpread);
        softly.assertThat(minMaxTradeSizeOnTable)
                .as("minMaxTradeSize is not as expected")
                .isEqualTo(minMaxTradeSize);
        softly.assertThat(marginRequirementOnTable)
                .as("marginRequirement is not as expected")
                .isEqualTo(marginRequirement);
        softly.assertThat(longSwapOnTable)
                .as("longSwap is not as expected")
                .isEqualTo(longSwap);
        softly.assertThat(shortSwapOnTable)
                .as("shortSwap is not as expected")
                .isEqualTo(shortSwap);
        softly.assertAll();
    }
}
