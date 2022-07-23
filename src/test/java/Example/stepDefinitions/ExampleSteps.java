package Example.stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExampleSteps {
    WebDriver driver;

    @Given("^Open facebook application$")
    public void open_facebook_application() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @When("^Input to Username textbox$")
    public void input_to_username_textbox() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("automationtesting@gmail.com");
    }

    @And("^Input to Password textbox$")
    public void input_to_password_textbox() {
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys("123456");
    }

    @When("^Input to Username textbox \"([^\"]*)\"$")
    public void input_to_username_textbox(String email) {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("^Input to Password textbox \"([^\"]*)\"$")
    public void input_to_password_textbox(String password) {
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    @When("^Input to Username textbox ([^\"]*)$")
    public void input_to_username_textbox_no_mark(String email) {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("^Input to Password textbox ([^\"]*)$")
    public void input_to_password_textbox_no_mark(String password) {
        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    @When("^Input to Username with \"([^\"]*)\" and Password \"([^\"]*)\"$")
    public void input_to_username_with_and_password(String email, String password) {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(password);
    }

    @And("^Click to Submit button$")
    public void click_to_submit_button() {
        driver.findElement(By.name("login")).click();

    }

    @Then("^Verify email textbox is displayed$")
    public void verify_email_textbox_is_displayed() {
        Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
    }

    @And("^Verify password textbox is displayed$")
    public void verify_password_textbox_is_displayed() {
        Assert.assertTrue(driver.findElement(By.id("pass")).isDisplayed());
    }

    @And("^Close application$")
    public void close_application() {
        driver.quit();
    }

    @When("^Input to Username and Password$")
    public void input_to_username_and_password(DataTable table) {
        List<Map<String, String>> customer = table.asMaps(String.class, String.class);

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(customer.get(0).get("Username"));

        driver.findElement(By.id("pass")).clear();
        driver.findElement(By.id("pass")).sendKeys(customer.get(0).get("Password"));

//    	for (Map<String, String> loginInfo : table.asMaps(String.class, String.class)) {
//        	driver.findElement(By.id("email")).clear();
//        	driver.findElement(By.id("email")).sendKeys(loginInfo.get("Username"));
//
//        	driver.findElement(By.id("pass")).clear();
//        	driver.findElement(By.id("pass")).sendKeys(loginInfo.get("Password"));
//    	}

    }


    @When("^Open Customer page$")
    public void open_customer_page() {

    }

    @Then("^Verify submitted info displayed$")
    public void verify_submitted_info_displayed() {

    }

    @And("^Input to Customer Name textbox (.+)$")
    public void input_to_customer_name_textbox(String customername) {

    }

    @And("^Input to City textbox (.+)$")
    public void input_to_city_textbox(String city) {

    }

    @And("^Input to other required fields$")
    public void input_to_other_required_fields() {

    }
}
