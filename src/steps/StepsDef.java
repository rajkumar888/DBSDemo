package steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import runner.Runner;

public class StepsDef extends Runner {

	WebElement element;

	@Given("user has launched the url")
	public void user_has_launched_the_url() {
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
		driver.get("https://www.dbs.com.sg/personal/default.page");
	}

	@When("^user clicks on Learn More button$")
	public void user_clicks_on_Learn_More_button() {

		element = driver.findElement(By.xpath("//button[.='Learn more']"));
		element.click();
	}
	
	
	@When("^user clicks on Cards link$")
	public void user_clicks_on_Cards_link() {

		element = driver.findElement(By.xpath("//a[(text()='Cards']"));
		actions.moveToElement(element).click().build().perform();
	}
	
	@When("^user clicks on Credit Cards link$")
	public void user_clicks_on_Credit_Cards_link() {

		element = driver.findElement(By.xpath("//a[text()='Credit Cards']"));
		actions.moveToElement(element).click().build().perform();
	}
	
	
	
	@When("^user selects the two cards$")
	public void user_selects_the_two_cards() {

		element = driver.findElement(By.xpath("//input[@id='cb0']/following-sibling::label/div/span"));
		actions.moveToElement(element).click().build().perform();
		
		element = driver.findElement(By.xpath("//input[@id='cb1']/following-sibling::label/div/span"));
		actions.moveToElement(element).click().build().perform();
	}
	
	
	@When("^user clicks on compare button$")
	public void user_clicks_on_compare_button() {

		element = driver.findElement(By.xpath("//button[@id='cardCompareBtn']"));
		actions.moveToElement(element).click().build().perform();
	}
	
	@When("^user verifies the following details$")
	public void user_verifies_the_following_details(DataTable table) {

		List<List<String>> rows = table.asLists(String.class);
//		List<List> data = table.raw();
		
		SoftAssert softAssert = new SoftAssert();

		String locator="//div[@class='cardheader'][text()='Card Type']/following-sibling::div[1]";
		String expectedValue=rows.get(1).get(0);
		element = driver.findElement(By.xpath(locator));
		String actualValue=element.getText();
		softAssert.assertEquals(actualValue, expectedValue, "Values are not matching");
		
		locator="(//div[@class='sub-header'][text()='VISA'])[2]";
		expectedValue=rows.get(1).get(1);
		element = driver.findElement(By.xpath(locator));
		actualValue=element.getText();
		softAssert.assertEquals(actualValue, expectedValue, "Values are not matching");
		
		// similarly we can assert the entire table with actual values
		
		softAssert.assertAll();
		System.out.print("Assertion done successfully");
		
	}
	
	
	@When("user Scroll down and navigates to Singapore on the left panel")
	public void user_Scroll_down_and_navigates_to_Singapore_on_the_left_panel() {

	}

	

}
