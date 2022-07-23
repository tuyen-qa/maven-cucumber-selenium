package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CommonPageUI;

public class CommonPageObject extends BasePage {
    private WebDriver driver;

    public CommonPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
        waitForAllElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
        sendkeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
    }

    public void clickToButtonByText(WebDriver driver, String buttonText) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, CommonPageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
    }

    public void selectToDropdownByName(WebDriver driver, String dropdownAttributeName, String itemValue) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
        selectItemInDefaultDropdown(driver, CommonPageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownAttributeName);
    }

    public void clickToRadioButtonByLabel(WebDriver driver, String radioLabel) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_RADIO_BY_TEXT, radioLabel);
        clickToElement(driver, CommonPageUI.DYNAMIC_RADIO_BY_TEXT, radioLabel);
    }

	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabel) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_CHECKBOX_BY_TEXT, checkboxLabel);
		clickToElement(driver, CommonPageUI.DYNAMIC_CHECKBOX_BY_TEXT, checkboxLabel);
	}

	public String getDropdownValueByName(WebDriver driver, String dropdownAttributeName) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_DROPDOWN_BY_NAME, dropdownAttributeName);
		return getFirstSelectedItemDefaultDropdown(driver, CommonPageUI.DYNAMIC_DROPDOWN_BY_NAME,dropdownAttributeName);
	}

    public boolean isRadioButtonByLabelSelected(WebDriver driver, String radioLabel) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_RADIO_BY_TEXT, radioLabel);
        return getWebElement(driver, CommonPageUI.DYNAMIC_RADIO_BY_TEXT, radioLabel).isSelected();
    }

    public String getTextboxValueByID(WebDriver driver, String textboxID) {
        waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_ID,textboxID);
        return getElementAttribute(driver, CommonPageUI.DYNAMIC_TEXTBOX_BY_ID, "value",textboxID);
    }
    public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
        clickToElement(driver, CommonPageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
    }

    public void clickToLinkByText(WebDriver driver, String linkText) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_LINK_BY_TEXT, linkText);
        clickToElement(driver, CommonPageUI.DYNAMIC_LINK_BY_TEXT, linkText);
    }
}
