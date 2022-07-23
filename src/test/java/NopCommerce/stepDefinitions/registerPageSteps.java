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
import pageUIs.RegisterPageUI;
import utilities.DataUtil;

public class registerPageSteps {
    private WebDriver driver;
    static UserRegisterPageObject registerPage;
    static UserHomePageObject homePage;
    static UserCustomerInfoPageObject myAccountPage;
    private DataUtil dataTest;
    String userHomePageUrl, password;

    // Constructor : luôn luôn gọi đầu tiên
    public registerPageSteps() {
        this.driver = Hooks.openAndQuitBrowser();
        registerPage = PageGeneratorManager.getUserRegisterPage(driver);
        dataTest = DataUtil.getData();
    }

    @And("Click to register button")
    public void clickToRegisterButton() {
        registerPage.clickToRegisterButton();
    }

    @Then("Validate error message at firstname textbox")
    public void validateErrorMessageAtFirstnameTextbox() {
        Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
    }

    @And("Validate error message at lastname textbox")
    public void validateErrorMessageAtLastnameTextbox() {
        Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
    }

    @And("Validate error message at email textbox")
    public void validateErrorMessageAtEmailTextbox() {
        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
    }

    @And("Validate error message at password textbox")
    public void validateErrorMessageAtPasswordTextbox() {
        Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
    }

    @And("Validate error message at confirm password textbox")
    public void validateErrorMessageAtConfirmPasswordTextbox() {
        Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
    }

    @And("Input to firstname textbox")
    public void inputToFirstnameTextbox() {
        registerPage.inputToFirstnametextbox(dataTest.getFirstName());
    }

    @And("Input to lastname textbox")
    public void inputToLastnameTextbox() {
        registerPage.inputToLastnametextbox(dataTest.getLastName());
    }

    @And("Input to email textbox as ([^\"]*)$")
    public void inputToEmailTextboxAsEmail(String email) {
        registerPage.inputToEmailtextbox(email);
    }

    @And("Input to password textbox")
    public void inputToPasswordTextbox() {
        password = dataTest.getPassword();
        registerPage.inputToPasswordtextbox(password);
    }

    @And("Input to confirm password textbox")
    public void inputToConfirmPasswordTextbox() {
        registerPage.inputToConfirmPasswordtextbox(password);
    }

    @Then("Validate error message at email textbox as ([^\"]*)$")
    public void validateErrorMessageAtEmailTextboxAsValidation(String validation) {
        Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), validation);
    }

    @Then("^Register Success Message is displayed with \"([^\"]*)\"$")
    public void message_is_displayed_with(String validation) {
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), validation);
    }
}
