package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomer;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import utilities.ReadConfig;

public class BaseClass {

	WebDriver driver;
	LoginPage loginPage;
	AddCustomer addCustomerPage;
	SearchCustomerPage searchCustomerPage;
	ReadConfig readConfig = new ReadConfig();
	public static Logger logger;
	public Properties properties;

	// To generating random string for unique emailID
	public String randomeString() {
		return RandomStringUtils.randomAlphabetic(6);
	}

}