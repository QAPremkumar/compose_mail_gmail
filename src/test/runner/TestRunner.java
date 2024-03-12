import io.cucumber.junit.Cucumber;
        import io.cucumber.junit.CucumberOptions;
        import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "your.package.name", // Change this to the package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class GmailComposingTestRunner {
}