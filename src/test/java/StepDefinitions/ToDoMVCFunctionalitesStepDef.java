package StepDefinitions;

import org.openqa.selenium.interactions.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.drivers.Driver;
import com.pages.ToDoMVCPage;
import com.utilities.Constants;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.awt.AWTException;	
import java.awt.Robot;	
import java.awt.event.KeyEvent;	

public class ToDoMVCFunctionalitesStepDef {

	private ToDoMVCPage todoPg = new ToDoMVCPage(Driver.getDriver());
	
	List<List<String>> list = null;
	WebDriverWait wait= new WebDriverWait (Driver.getDriver(), 20);
	WebElement fltr = null;
	
	JavascriptExecutor executor = (JavascriptExecutor) Driver.getDriver();
	Actions act = new Actions(Driver.getDriver());
	
	@Given("user launches todomvc page url")
	public void user_launches_todomvc_page_url() {
		todoPg.navToURL(Constants.baseURL);
	}
	
	@Then("user should be navigated to the home page")
	public void verifyLandingPage() {
		Assert.assertEquals(todoPg.getCurrentURL(), Constants.baseURL);
	}
	
	@And("^I should see \"(.*)\" as page title$")
	public void verifyPageTitle(String title) {
		Assert.assertEquals(todoPg.getPageTitle(), title);
	}
	
	@When("user creates a list with following items")
	public void createList(DataTable dataTable) throws InterruptedException {
		list = dataTable.asLists(String.class);
		for(List<String> l:list) {
			todoPg.typeAndEnter(l);
		}
	}
	
	@When("user creates a list with following items but do not press enter")
	public void createListNoEnter(DataTable dataTable) throws InterruptedException {
		list = dataTable.asLists(String.class);
		for(List<String> l:list) {
			todoPg.typeButNoEnter(l);
		}
	}
	
	@When("user refreshes the page")
	public void refresh() {
		Driver.getDriver().navigate().refresh();
	}
	
	@Then("I should see a list created with following items")
	public void verifyCreatedLst(List<String> list) throws InterruptedException {
		for(String l:list) {
			WebElement cBox = Driver.getDriver().findElement(By.xpath("//label[contains(text(),'" + l + "')]"));
			Assert.assertTrue(cBox.isDisplayed() && cBox.isEnabled());
		}
		Assert.assertEquals(list.size(), todoPg.checkToValNoOfToDoItems()/2);
		Assert.assertEquals(list.size(), Integer.parseInt(todoPg.getTotItems()));
	}
	
	@Then("I should see following completed items listed")
	public void verifyCompLst(List<String> list) throws InterruptedException {
		for(String l:list) {
			WebElement cBox = Driver.getDriver().findElement(By.xpath("//label[contains(text(),'" + l + "')]"));
			Assert.assertTrue(cBox.isDisplayed() && cBox.isEnabled());
		}
	}
	/*public void verifyCreatedLst(List<String> list) throws InterruptedException {
		for(String l:list) {
			WebElement cBox = Driver.getDriver().findElement(By.xpath("//label[contains(text(),'" + l + "')]/preceding-sibling::input[@type='checkbox']"));
			WebElement dBtn = Driver.getDriver().findElement(By.xpath("//label[contains(text(),'" + l + "')]/following-sibling::button[@class='destroy']"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + l + "')]/preceding-sibling::input[@type='checkbox']")));
			System.out.println(cBox);
			System.out.println(dBtn);
			Assert.assertTrue(cBox.isDisplayed());
			Assert.assertTrue(dBtn.isDisplayed());
		}
	}*/
	
	
	@Then("I should see All, Active, Completed filters in footer")
	public void verifyFooter() {
		Assert.assertTrue(todoPg.filtersValAll());
		Assert.assertTrue(todoPg.filtersValActive());
		Assert.assertTrue(todoPg.filtersValCompleted());
	}	
	
	@And("I click on {int}(st|nd|rd|th) checkbox to mark the item as completed")
	public void verifyFooterFilter(int row) {
		todoPg.markAsCompleted(row);
	}	
	
	@Then("I should see following items listed")
	public void verifyModifiedLst(List<String> list) throws InterruptedException {
		verifyCreatedLst(list);
	}
	
	@Then("I click on {string} filter from footer")
	public void clckFltrFooter(String filName) throws InterruptedException {
		WebElement fltr = Driver.getDriver().findElement(By.xpath("//ul[@class='filters']//a[contains(text(),'" + filName + "')]"));
		fltr.click();
	}
	
	@Then("I should not see any completed todo items in the list")
	public void cntCmpltdItems() throws InterruptedException {
		Assert.assertEquals(todoPg.cntCompItems(), 0);
	}
	
	@Then("I should see only completed todo items in the list")
	public void cntActiveItems() throws InterruptedException {
		Assert.assertEquals(todoPg.cntActvItems(), 0);
	}
	
	@Then("I should see base url ends with {string}")
	public void urlVerification(String a) {
		Assert.assertEquals(Driver.getDriver().getCurrentUrl(), Constants.baseURL+a);
	}
	
	@Then("I click on {string} button from footer")
	public void btnClck(String a) {
		fltr = Driver.getDriver().findElement(By.xpath("//button[contains(text(),'" + a + "')]"));
		fltr.click();
	}
	
	@Then("I click on down arrow to mark all items as completed")
	public void markAllCmpltd() {
		todoPg.clckMarkAllComplete();
	}
	
	@And("I should see {string} in the footer")
	public void msgInFooter(String msg) {
		Assert.assertEquals(todoPg.getMsgFooter(), msg);
	}
	
	@And("I click on destory button next to {string}")
	public void clckDestroyBtn(String a) {
		fltr = Driver.getDriver().findElement(By.xpath("//li[contains(@class,'todo')]//label[contains(text(),'" + a + "')]/following-sibling::button[@class='destroy']"));
		executor.executeScript("arguments[0].click();", fltr);
	}
	
	@And("I edit {string} as {string} in the list")
	public void editAnItem(String old, String newItem) throws InterruptedException {
		fltr = Driver.getDriver().findElement(By.xpath("//li[contains(@class,'todo')]//label[contains(text(),'" + old + "')]"));
		Action seriesOfActions = act
				.doubleClick(fltr)
				.sendKeys(Driver.getDriver().findElement(By.xpath("//li[contains(@class,'todo')]/input[@type='text'][@class='edit']")), newItem)
				.sendKeys(Keys.RETURN)
				.build();
		seriesOfActions.perform();
	}
	
	@And("I should see {string} displayed in the home page")
	public void chkPHldr(String a) {
		Assert.assertTrue(todoPg.pHldrVal());
	}
	
	@And("I should not see any to do item in the list")
	public void noToDoItem() {
		Assert.assertEquals(todoPg.checkToValNoOfToDoItems(), 0);
	}
	
	@When("user launches todomvc page url in new tab of same browser session")
	public void createNewBSession() throws AWTException, InterruptedException {
	    //Driver.getDriver().findElement(By.xpath("//body")).sendKeys(Keys.CONTROL +"t");

		Robot robot = new Robot();                          
		robot.keyPress(KeyEvent.VK_CONTROL); 
		robot.keyPress(KeyEvent.VK_T); 
		robot.keyRelease(KeyEvent.VK_CONTROL); 
		robot.keyRelease(KeyEvent.VK_T);
		Thread.sleep(5000);
		//Switch focus to new tab
		ArrayList<String> tabs = new ArrayList<String> (Driver.getDriver().getWindowHandles());
		Driver.getDriver().switchTo().window(tabs.get(1));

		//Launch URL in the new tab
		Driver.getDriver().get(Constants.baseURL);
	}
	
}
