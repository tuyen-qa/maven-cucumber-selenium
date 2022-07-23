package pageUIs;

public class CommonPageUI {

    public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='My product reviews']";
    public static final String ADDRESS_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Addresses']";
    public static final String REWARD_POINT_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Reward points']";
    public static final String CUSTOMER_INFO_LINK = "xpath=//div[contains(@class,'account-navigation')]//a[text()='Customer info']";
    public static final String LOGOUT_LINK_AT_USER = "xpath=//a[@class='ico-logout']";
    public static final String LOGOUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
    public static final String AJAX_BUSY = "xpath=//div[@id='ajaxBusy']";

    public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
    public static final String DYNAMIC_PAGE_HEADER = "xpath=//div[@class='header']//a[text()='%s']";
    public static final String DYNAMIC_RADIO_BY_TEXT = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String DYNAMIC_CHECKBOX_BY_TEXT = "xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
    public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";

    public static final String DYNAMIC_TEXTBOX_ERROR_MESSAGE_BY_TEXT = "xpath=//span[@id='%s-error']";

    public static final String DYNAMIC_LINK_BY_TEXT = "xpath=//a[text()='%s']";
}
