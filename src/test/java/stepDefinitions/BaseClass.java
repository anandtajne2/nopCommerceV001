package stepDefinitions;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomer;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class BaseClass {

	WebDriver driver;
	LoginPage loginPage;
	AddCustomer addCustomerPage;
	ReadConfig readConfig = new ReadConfig();

	// To generating random string for unique emailID
	public String randomeString() {
		return RandomStringUtils.randomAlphabetic(6);
	}

}