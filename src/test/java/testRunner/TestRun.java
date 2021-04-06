package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./Features\\Login.feature", glue = "stepDefinitions", monochrome = true, dryRun = false, plugin = {
		"pretty", "html:target\\HTMLReports", "json:target\\JSONReports\\report.json",
		"junit:target\\JUNITReports\\reports.xml" })
public class TestRun {

}