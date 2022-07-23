package cucumberOption;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;



@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Example/features",
        glue = "Example/stepDefinitions",
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports","json:target/cucumber.json"},
        snippets = SnippetType.CAMELCASE,
        tags = "@datatable_step")

public class ExampleTestRunner {
}
