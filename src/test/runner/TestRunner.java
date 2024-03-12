package org.hplx.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/java/org/hplx/feature_files",
        glue = "org.hplx.stepdefinations",
        plugin = {
                "pretty",
                "html:test-results/DefaultReport.html",
                "json:target/cucumber.json",
                "json:target/cucumber.xml",
                "json:target/cucumber-report/cucumber.json",
                "rerun:target/failedrerun.txt",
                "timeline:target/threadTimelines",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },
        publish = true,
        tags = "@pdf-testing and not @ignore",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}