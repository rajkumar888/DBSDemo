package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.core.gherkin.vintage.internal.gherkin.ast.DataTable;
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

	@When("user clicks on Learn More button")
	public void user_clicks_on_Learn_More_button() {

		element = driver.findElement(By.xpath("//button[.='Learn more']"));
		element.click();

	}

	@When("user Scroll down and navigates to Singapore on the left panel")
	public void user_Scroll_down_and_navigates_to_Singapore_on_the_left_panel() {

	}

	@When("user Read and writes the table in excel sheet")
	public void user_Read_and_writes_the_table_in_excel_sheet() {

	}

	@When("user Navigates to About in the header tabs")
	public void user_Navigates_to_About_in_the_header_tabs() {

	}

	@When("user Navigates to Who we are tab")
	public void user_Navigates_to_Who_we_are_tab() {

	}

	@When("user Navigates to  Our Awards & Accolades")
	public void user_Navigates_to_Our_Awards_Accolades() {

	}

	@Then("Validates the total number of awards displayed on the page is {int}")
	public void validates_the_total_number_of_awards_displayed_on_the_page_is(Integer int1) {

	}

	@Then("Validates all the award name and caption of the awards mentioned in the below table")
	public void validates_all_the_award_name_and_caption_of_the_awards_mentioned_in_the_below_table(
			DataTable dataTable) {

	}

	@When("user prints in the cucumber report in the form of a table")
	public void user_prints_in_the_cucumber_report_in_the_form_of_a_table() {

	}

}
