package org.com.tasks.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.com.tasks.ui.utils.Utils.waitForElement;

public class StocksUtil {

    private StocksUtil() {
        //nothing here
    }

    public static String getDataFromTable(WebElement rowParentElement, String cell) {
        waitForElement(rowParentElement);
        return rowParentElement.findElement(By.xpath(".//td[@data-xm-qa-name='" + cell + "']")).getText();
    }
}
