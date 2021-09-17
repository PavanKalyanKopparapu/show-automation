package show.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import show.constants.LogConstants;
import show.ui.Common;
import show.util.UtilFactory;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Managed;

/**
 * This class contains the step definition for the Common operations perform in different master pages
 * 
 * @author Parth Moradiya
 * 
 *
 */

public class CommonStepDef extends PageObject {
	UtilFactory utilFactory = new UtilFactory(super.getDriver());
	private static Logger logger = LoggerFactory.getLogger(CommonStepDef.class);
	public WebDriver driver;
	@Managed
	Common common;
	Scenario scenario;

	public CommonStepDef() {
		driver = super.getDriver();
		new UtilFactory(driver);
	}

	@Before
	public void initScenario(Scenario scenario) {
	    this.scenario = scenario;
	}
	
	@Then("^User should navigate to (.*)$")
	public void navigateToMaster(String tabType) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		logger.info("I am on home page");
		common.goToMaster(tabType);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@And("^Clicks on '(.*)' from css dashboard")
	public void gotoLink(String linkName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.gotoLinkFromCss(linkName);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Given("^User on '(.*)' page")
	public void gotoLabel(String labelName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.labelForCss(labelName);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Then("User gets (.*) growl message")
	public void assertGrowlMessage(String methodName, String scenarioId) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		String message = utilFactory.readTestDataFromJSON(scenario, scenarioId, "message");
		if (methodName.equals("proper")) {
			assertEquals(message, common.getGrowlMessage());
		} else if (methodName.equals("contains")) {
			assertTrue(common.getGrowlMessageIfContainsText(message));
		} else if (methodName.equals("exect")) {
			assertEquals(message, common.getGrowlMessageIfHavingExactText(message));
		} else {
			Assert.fail("FAILED TO ASSERT ---> WRONG METHOD");
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@And("^User click on no button$")
	public void clickNo() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.clickNoPopup();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@And("^User click on yes button$")
	public void clickYes() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.clickYesPopup();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@And("^User navigate to sales master dashboard$")
	public void salesMasterDashboard() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.goToSalesMasterDashboard();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	@Then("User gets validation message for (.*) and (.*)")
	public void assertInputValidation(String scenarioId, String key) throws Exception {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			assertTrue(common.checkInputValidations(
					utilFactory.readTestDataFromJSON(scenario, scenarioId, key)));
		} catch (WebDriverException | AssertionError e) {
			Assert.fail("No assertion message found!!!");
		} catch (IllegalStateException e) {
			Assert.fail("No assertion message found!!!");
		} catch (Exception e) {
			Assert.fail("No assertion message found!!!");
		}
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@And("^User will be navigate to dashboard")
	public void gotoLinkName() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.gotoLinkFromDashboard();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@And("^User close pop-up box")
	public void closePopup() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.clickClosePopup();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@And("^User will close (.*) tab$")
	public void closeTab(String tabName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.clickCloseTab(tabName);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@And("^User click on filter icon")
	public void filterIcon() {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.clickFilterIcon();
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@And("^User search and filter '(.*)' from '(.*)' column$")
	public void filterAndSearch(String searchName, String columnName) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.applyFilterAndSearch(searchName,columnName);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@And("^User click on (.*) button on filter$")
	public void buttonFilter(String buttonType) {
		logger.debug(LogConstants.LOG_ENTER + Thread.currentThread().getStackTrace()[1].getMethodName());
		common.buttonFilterIcon(buttonType);
		logger.debug(LogConstants.LOG_EXIT + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

}