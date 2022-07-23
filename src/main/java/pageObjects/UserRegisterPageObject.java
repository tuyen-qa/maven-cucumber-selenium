package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

    }

    public UserHomePageObject clickToLogoutButton() {
        waitForElementClickable(driver, RegisterPageUI.LOGOUT_BUTTON);
        clickToElement(driver, RegisterPageUI.LOGOUT_BUTTON);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public void inputToFirstnametextbox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);

    }

    public void inputToLastnametextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);

    }

    public void inputToEmailtextbox(String emailAddress) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);

    }

    public void inputToPasswordtextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);

    }

    public void inputToConfirmPasswordtextbox(String confirmPassword) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD);
        sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD, confirmPassword);

    }

    public String getErrorMessageAtFirstnameTextbox() {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtLastnameTextbox() {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getErrorMessageAtEmailTextbox() {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getErrorMessageAtPasswordTextbox() {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getErrorMessageAtConfirmPasswordTextbox() {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getErrorMessageUnsuccessfull() {
        waitForElementVisible(driver, RegisterPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
    }
}
