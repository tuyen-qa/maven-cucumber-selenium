package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.*;

public class PageGeneratorManager {	public static UserHomePageObject getUserHomePage(WebDriver driver) {
    return new UserHomePageObject(driver);
}

    public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
        return new UserRewardPointPageObject(driver);
    }

    public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
        return new UserMyProductReviewPageObject(driver);
    }

    public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
        return new UserAddressPageObject(driver);
    }

    public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
        return new UserCustomerInfoPageObject(driver);
    }

    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }

    public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }

    public static CommonPageObject getCommonPage(WebDriver driver) {
        return new CommonPageObject(driver);
    }
}
