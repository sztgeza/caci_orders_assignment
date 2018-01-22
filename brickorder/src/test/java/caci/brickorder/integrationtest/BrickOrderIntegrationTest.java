package caci.brickorder.integrationtest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        format = {"html:target/cucumber-report/brickOrderIntegration",
                "json:target/cucumber-report/brickOrderIntegration.json"})
public class BrickOrderIntegrationTest {

}
