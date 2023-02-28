package cucumber.options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",plugin = "html:target/cucumber-report.html",glue={"stepDefination"},tags= "@DeletePlace")

public class TestRunner {
}
//,tags= "@DeletePlace"
//,plugin = "json:target/jsonReports/cucumber-report.json"