package cucumberOption;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/NopCommerce/features",
        glue = "NopCommerce/stepDefinitions",
        monochrome = true,
//        html cucumber report default là loại report mặc định của cucumber, nó sẽ sinh ra file json.
//        file json được dùng để làm input cho report của net.masterthought
        plugin = {"pretty", "html:target/site/cucumber-report-default","json:target/site/cucumber.json"},
        snippets = SnippetType.CAMELCASE,
        tags = "  @TC_03_Register_Successfully_Option_2")

public class NopCommerceTestRunner {
}
