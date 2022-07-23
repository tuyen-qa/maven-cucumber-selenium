package NopCommerce.stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOption.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageObjects.UserCustomerInfoPageObject;
import pageObjects.UserHomePageObject;
import pageObjects.UserRegisterPageObject;

public class userCustomerInfoPageSteps {
    WebDriver driver;
    static UserCustomerInfoPageObject myAccountPage;
    static UserRegisterPageObject registerPage;
    static UserHomePageObject homePage;

    public userCustomerInfoPageSteps(){
        this.driver = Hooks.openAndQuitBrowser();
        myAccountPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
    }

}
