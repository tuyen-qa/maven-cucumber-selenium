package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserAddressPageObject extends BasePage {
    private WebDriver driver;

    public UserAddressPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
