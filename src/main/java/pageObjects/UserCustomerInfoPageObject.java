package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {

    private WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCustomerInfoPageDisplayed() {
        waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
        return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
    }
}
