package jesus.rodrigo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        features={"src/test/resources/features"},
        glue = {"jesus.rodrigo.steps"}
)
public class PetStoreTest {
}
