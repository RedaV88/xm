package org.com.tasks.ui.tests.pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
public class StocksPage extends BasePage {
    String minSpread = "minSpread";
    String minMaxTradeSize = "minMaxTradeSize";
    String marginRequirement = "marginRequirement";
    String swapLong = "swapLong";
    String swapShort = "swapShort";
    String limitStopLevel = "limitStopLevel";

    @FindBy(xpath = "//button[@data-value='Norway']")
    private WebElement norwayButton;
    @FindBy(id = "DataTables_Table_0_next")
    private WebElement nextTableButton;
    @FindBy(xpath = "//td[contains(text(),'Orkla ASA (ORK.OL)')]/..")
    private WebElement orklaAsaRow;
    @FindBy(xpath = "//td[contains(text(),'Orkla ASA (ORK.OL)')]/..//a[contains(text(),'Read More')]")
    private WebElement orklaReadMore;

    public StocksPage(WebDriver driver) {
        super(driver);
    }


}