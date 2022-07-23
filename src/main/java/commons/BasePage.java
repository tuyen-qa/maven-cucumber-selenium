package commons;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.*;
import pageUIs.CommonPageUI;
import pageUIs.HomePageUI;

public class BasePage {
    private final long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private final long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendKeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(windowID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String tabTitle) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(tabTitle)) {
                break;
            }
        }
    }

    public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    private By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")
                || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=")
                || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath")
                || locatorType.startsWith("XPath=") || locatorType.startsWith("XPATH=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported!");
        }
        return by;
    }

    public WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
    }

    public List<WebElement> getWebElements(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElements(driver, getDynamicXpath(locatorType, dynamicValue));
    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getWebElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValue) {
        getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValue) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        element.clear();
        element.sendKeys(textValue);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
        Select select = new Select(getWebElement(driver, locatorType));
        select.selectByVisibleText(textItem);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem,
                                            String... dynamicValue) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        select.selectByVisibleText(textItem);
    }

    public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    public String getFirstSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValue) {
        Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getWebElement(driver, locatorType));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
                                           String expectedTextItem) {
        getWebElement(driver, parentXpath).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);

        List<WebElement> allItems = explicitWait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);
                item.click();
                break;
            }
        }

    }

    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
        return getWebElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locatorType, String attributeName,
                                      String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).getText().trim();
    }

    public String getElementText(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).getText().trim();
    }

    public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
        return getWebElement(driver, locatorType).getCssValue(propertyName);
    }

    public String getElementValueByJS(WebDriver driver, String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        xpathLocator = xpathLocator.replace("xpath=", "");
        return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator
                + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
    }

    public String getHexaColorFromRgba(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String locatorType) {
        return getListWebElement(driver, locatorType).size();
    }

    public int getElementSize(WebDriver driver, String locatorType, String... dynamicValue) {
        return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).size();
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValue) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValue) {
        WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValue));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType) {
        try {
            return getWebElement(driver, locatorType).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

//	public boolean isElementUndisplayed(WebDriver driver, String locator) {
//		System.out.println("Start time : "+new Date().toString());
//		overrideImplicitTimeout(driver, shortTimeout);
//		List<WebElement> elements = getWebElements(driver, locator);
//		overrideImplicitTimeout(driver, longTimeout);
//		if (elements.size()==0) {
//			return true;
//		} else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
//			return true;
//		}else {
//			return false;
//		}
//	}

    // update in Framework - Topic 74 how to verify undisplayed elements
    public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(driver, shortTimeout);
        System.out.println("Start time for wait invisible=" + new Date().toString());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideGlobalTimeout(driver, longTimeout);
        System.out.println("End time for wait invisible=" + new Date().toString());
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(driver, shortTimeout);
        System.out.println("Start time for wait invisible=" + new Date().toString());
        explicitWait.until(ExpectedConditions
                .invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
        overrideGlobalTimeout(driver, longTimeout);
        System.out.println("End time for wait invisible=" + new Date().toString());
    }

    public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
        System.out.println("Start time = " + new Date().toString());
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getWebElements(driver, locatorType);
        overrideGlobalTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible/displayed");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else {
            System.out.println("Element in DOM and visible");
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
        System.out.println("Start time = " + new Date().toString());
        overrideGlobalTimeout(driver, shortTimeout);
        List<WebElement> elements = getWebElements(driver, getDynamicXpath(locatorType, dynamicValue));
        overrideGlobalTimeout(driver, longTimeout);

        if (elements.size() == 0) {
            System.out.println("Element not in DOM");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            System.out.println("Element in DOM but not visible/displayed");
            System.out.println("End time = " + new Date().toString());
            return true;
        } else {
            System.out.println("Element in DOM and visible");
            return false;
        }
    }

    public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isEnabled();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType, String...dynamicValue) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType) {
        return getWebElement(driver, locatorType).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType, String...dynamicValue ) {
        return getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)).isSelected();
    }

    public void switchToFrameIframe(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getWebElement(driver, locatorType));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locatorType) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locatorType)).perform();

    }

    public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValue) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue))).perform();

    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locatorType), key).perform();

    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValue) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)), key).perform();

    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void hightlightElement(WebDriver driver, String locatorType) {
        WebElement element = getWebElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
                "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
    }

    public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();",
                getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
    }

    public void scrollToElementOnTop(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
    }

    public void scrollToElementOnDown(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
                getWebElement(driver, locatorType));
    }

    public String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
                getWebElement(driver, locatorType));
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locatorType));
        return status;
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValue) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, getDynamicXpath(locatorType, dynamicValue)));
        return status;
    }

    public void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

//	public void waitForElementInvisible(WebDriver driver, String locatorType) {
//		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
//	}
//
//	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValue) {
//		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
//	}

    public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
    }

    public void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions
                .invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValue))));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValue) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(
                ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValue))));
    }

    public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        return explicitWait.until(jQueryLoad);
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    /*
     * Wait for Element undisplayed in DOM or not in DOM and override
     * implicitTimeout
     */
    public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideImplicitTimeout(driver, shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(driver, longTimeout);
    }

    public String getDynamicXpath(String locatorType, String... dynamicValue) {
        System.out.println("Locator type before = " + locatorType);
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")
                || locatorType.startsWith("XPath=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValue);
        }
        System.out.println("Values map to locator = " + dynamicValue);
        System.out.println("Locator type after = " + locatorType);
        return locatorType;
    }

    public UserRegisterPageObject openUserRegisterPage(WebDriver driver) {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getUserRegisterPage(driver);
    }

    public UserLoginPageObject openUserLoginPage(WebDriver driver) {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver, HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);

    }

    public UserAddressPageObject openUserAddressPage(WebDriver driver) {
        waitForElementClickable(driver, CommonPageUI.ADDRESS_LINK);
        clickToElement(driver, CommonPageUI.ADDRESS_LINK);
        return PageGeneratorManager.getUserAddressPage(driver);
    }

    public UserMyProductReviewPageObject openUserMyProductReviewPage(WebDriver driver) {
        waitForElementClickable(driver, CommonPageUI.MY_PRODUCT_REVIEW_LINK);
        clickToElement(driver, CommonPageUI.MY_PRODUCT_REVIEW_LINK);
        return PageGeneratorManager.getUserMyProductReviewPage(driver);
    }

    public UserRewardPointPageObject openUserRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, CommonPageUI.REWARD_POINT_LINK);
        clickToElement(driver, CommonPageUI.REWARD_POINT_LINK);
        return PageGeneratorManager.getUserRewardPointPage(driver);
    }

    public UserCustomerInfoPageObject openUserCustomerInfoPage(WebDriver driver) {
        waitForElementClickable(driver, CommonPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, CommonPageUI.CUSTOMER_INFO_LINK);
        return PageGeneratorManager.getUserCustomerInfoPage(driver);
    }

    public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
        waitForElementClickable(driver, CommonPageUI.LOGOUT_LINK_AT_USER);
        clickToElement(driver, CommonPageUI.LOGOUT_LINK_AT_USER);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
        waitForElementInvisible(driver, CommonPageUI.AJAX_BUSY);
        waitForElementClickable(driver, CommonPageUI.LOGOUT_LINK_AT_ADMIN);
        clickToElement(driver, CommonPageUI.LOGOUT_LINK_AT_ADMIN);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }

    public BasePage openPageAtMyAccountByText(WebDriver driver, String pageName) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_BUTTON_BY_TEXT, pageName);
        clickToElement(driver, CommonPageUI.DYNAMIC_BUTTON_BY_TEXT, pageName);
        switch (pageName) {
            case "My account":
                return PageGeneratorManager.getUserCustomerInfoPage(driver);
            case "Addresses":
                return PageGeneratorManager.getUserAddressPage(driver);
            case "My product reviews":
                return PageGeneratorManager.getUserMyProductReviewPage(driver);
            case "Reward points":
                return PageGeneratorManager.getUserRewardPointPage(driver);
            case "Register":
                return PageGeneratorManager.getUserRegisterPage(driver);

            default:
                throw new RuntimeException("Invalid page name at My Account area.");
        }
    }

    public BasePage openPageWithLinkByText(WebDriver driver, String pageName) {
        waitForElementClickable(driver, CommonPageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
        clickToElement(driver, CommonPageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
        switch (pageName) {
            case "Customer info":
                return PageGeneratorManager.getUserCustomerInfoPage(driver);
            case "Addresses":
                return PageGeneratorManager.getUserAddressPage(driver);
            case "My product reviews":
                return PageGeneratorManager.getUserMyProductReviewPage(driver);
            case "Reward points":
                return PageGeneratorManager.getUserRewardPointPage(driver);

            default:
                throw new RuntimeException("Invalid page name at My Account area.");
        }
    }

}
