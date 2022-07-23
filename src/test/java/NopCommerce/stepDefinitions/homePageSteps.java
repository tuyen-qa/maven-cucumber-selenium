package NopCommerce.stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOption.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.UserCustomerInfoPageObject;
import pageObjects.UserHomePageObject;
import pageObjects.UserRegisterPageObject;
import pageUIs.HomePageUI;
import utilities.DataUtil;

public class homePageSteps {
    private WebDriver driver;
    static UserRegisterPageObject registerPage;
    static UserHomePageObject homePage;
    static UserCustomerInfoPageObject myAccountPage;
    private DataUtil dataTest;
    static String userHomePageUrl, password;

    // Constructor : luôn luôn gọi đầu tiên
    public homePageSteps() {
        this.driver = Hooks.openAndQuitBrowser();
        homePage = PageGeneratorManager.getUserHomePage(driver);
        dataTest = DataUtil.getData();
    }

    @Given("Get User home page url")
    public void getUserHomePageUrl() {
        userHomePageUrl = homePage.getUserHomePageUrl();
    }


    @When("^Click to register button in home page$")
    public void clickToRegisterButtonInHomePage() {
        homePage.waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        homePage.clickToElement(driver, HomePageUI.REGISTER_LINK);
    }
}
