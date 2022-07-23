package NopCommerce.stepDefinitions;

import commons.PageGeneratorManager;
import cucumberOption.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageObjects.CommonPageObject;
import pageObjects.UserCustomerInfoPageObject;
import pageObjects.UserHomePageObject;
import pageObjects.UserRegisterPageObject;
import utilities.DataUtil;

public class commonPageSteps {
    private WebDriver driver;
    static CommonPageObject commonPage;
    static UserRegisterPageObject registerPage;
    static UserHomePageObject homePage;
    static UserCustomerInfoPageObject myAccountPage;

    private DataUtil dataTest;

    public commonPageSteps(){
        this.driver = Hooks.openAndQuitBrowser();
        commonPage = PageGeneratorManager.getCommonPage(driver);
        dataTest = DataUtil.getData();
    }


    @Then("^Valid text in \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
    public void valid_text_in_textbox_with_value(String textboxID, String valueTextbox) {
        Assert.assertEquals(valueTextbox,commonPage.getTextboxValueByID(driver,textboxID));
    }

    @And("^Click to \"([^\"]*)\" radio button$")
    public void click_to_radio_button(String labelRadioButton) {
        commonPage.clickToRadioButtonByLabel(driver, labelRadioButton);
    }

    @And("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
    public void input_to_textbox_with_value(String idTextbox, String valueTextbox) {
        commonPage.inputToTextboxByID(driver, idTextbox, valueTextbox);
    }

    @And("^Select \"([^\"]*)\" dropdown with value \"([^\"]*)\"$")
    public void select_dropdown_with_value(String nameDropdown, String valueDropdown) {
        commonPage.selectToDropdownByName(driver, nameDropdown, valueDropdown);
    }


    @And("^Radio button \"([^\"]*)\" is selected$")
    public void radio_button_is_selected(String labelRadioButton) {
        commonPage.isRadioButtonByLabelSelected(driver, labelRadioButton);
    }

    @And("^Valid text in \"([^\"]*)\" dropdown with value \"([^\"]*)\"$")
    public void valid_text_in_dropdown_with_value(String dropdownAttributeName, String valueDropdown) {
        Assert.assertEquals(valueDropdown, commonPage.getDropdownValueByName(driver,dropdownAttributeName));
    }

    @And("^Input to \"([^\"]*)\" textbox$")
    public void input_to_textbox(String idTextbox) {
        commonPage.inputToTextboxByID(driver, idTextbox, dataTest.getEmailAddress());
    }

    @When("^Click to \"([^\"]*)\" link$")
    public void click_to_link(String linkTex) {
        commonPage.clickToLinkByText(driver,linkTex);
    }

    @When("^Open Register page$")
    public void openRegisterPage() {
        registerPage = commonPage.openUserRegisterPage(driver);
    }

    @When("^Open My Account page$")
    public void openMyAccountPage() {
        myAccountPage = commonPage.openUserCustomerInfoPage(driver);
    }

}
