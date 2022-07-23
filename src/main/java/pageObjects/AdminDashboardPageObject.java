package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminDashBoardPageUI;

public class AdminDashboardPageObject extends BasePage {
    WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardHeaderDisplayed() {
        waitForElementVisible(driver, AdminDashBoardPageUI.DASHBOARD_HEADER);
        return isElementDisplayed(driver, AdminDashBoardPageUI.DASHBOARD_HEADER);
    }

}
