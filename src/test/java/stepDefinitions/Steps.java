package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddCustomer;
import pageObjects.LoginPage;

public class Steps extends BaseClass {

	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
		System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
		driver = new ChromeDriver();
		loginPage = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String username, String password) {
		loginPage.setUserName(username);
		loginPage.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() {
		loginPage.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String exptitle) {
		if (driver.getPageSource().contains("Login was unsuccessful")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(exptitle, driver.getTitle());
		}
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() {
		loginPage.clickLogout();
	}

	@Then("close browser")
	public void close_browser() {
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
		addCustomerPage.setEmail(randomeString() + "@gmail.com");
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
		addCustomerPage.clickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));
	}

}