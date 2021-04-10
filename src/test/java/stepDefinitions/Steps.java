package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddCustomer;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {

	@Before
	public void setup() throws IOException {
		logger.info("------------- setUp ----------------");
		properties = new Properties();
		FileInputStream configPropFile = new FileInputStream("./Configuration\\config.properties");
		properties.load(configPropFile);

		// System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
		String browser = properties.getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", properties.getProperty("chromepath"));
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", properties.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", properties.getProperty("IEpath"));
			driver = new InternetExplorerDriver();
		}

		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");
	}

	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
		logger.info("------------- lanunching browser ----------------");
		loginPage = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		logger.info("------------- Opening URL ----------------");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String username, String password) {
		logger.info("------------- Providing login details ----------------");
		loginPage.setUserName(username);
		loginPage.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() {
		logger.info("------------- started login process ----------------");
		loginPage.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String exptitle) {
		if (driver.getPageSource().contains("Login was unsuccessful")) {
			driver.close();
			logger.info("------------- login passed ----------------");
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(exptitle, driver.getTitle());
		}
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() {
		logger.info("------------- click on logout link ----------------");
		loginPage.clickLogout();
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("------------- closed browser ----------------");
		driver.close();
	}

//	Customer Feature Steps Defination

	@Then("User can view Dashboad")
	public void user_can_view_Dashboad() {
		addCustomerPage = new AddCustomer(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCustomerPage.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		addCustomerPage.clickOnCustomersMenu();
		Thread.sleep(3000);
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() {
		addCustomerPage.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		addCustomerPage.clickOnAddnew();
		Thread.sleep(3000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPage.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("------------- adding new customer ----------------");
		logger.info("------------- providing customer details ----------------");
		addCustomerPage.setEmail(randomeString() + "@gmail.com");
//		addCustomerPage.setEmail("victoria_victoria@nopCommerce.com");
		addCustomerPage.setPassword("test1234");
		addCustomerPage.setFirstName("anand");
		addCustomerPage.setLastName("tajne");
		addCustomerPage.setGender("Male");
		addCustomerPage.setDob("7/05/1985"); // Format: D/MM/YYY
		addCustomerPage.setCompanyName("busyQA");
		addCustomerPage.setCustomerRoles("Guest");
		Thread.sleep(2000);
		addCustomerPage.setAdminContent("This is for testing.........");
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logger.info("------------- saving customer data ----------------");
		addCustomerPage.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));
	}

//	steps of search by Email id
	@When("Enter customer EMail")
	public void enter_customer_EMail() {
		logger.info("------------- searching customer by email id ----------------");
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCustomerPage.clickSearch();
		Thread.sleep(2000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
		boolean status = searchCustomerPage.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}

	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("------------- searching customer by name ----------------");
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		searchCustomerPage.setLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		boolean status = searchCustomerPage.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}
}